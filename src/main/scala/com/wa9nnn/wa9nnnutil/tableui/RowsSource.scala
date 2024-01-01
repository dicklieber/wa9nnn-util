/*
 * Copyright (C) @year  Dick Lieber, WA9NNN
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.wa9nnn.wa9nnnutil.tableui

import com.wa9nnn.wa9nnnutil.CountedThings

/**
 * Adapt a case class to a [[ Seq[Row] ]]
 * Nested case classes that extend [[RowsSource]] will also have their members rendered as Rows.
 * case classes extend [[Product]] which allows introspecting the contents; which is the 'magic' behind this trait.
 */
trait RowsSource extends Product {

  def toRows(includeNone: Boolean = true, prefix: String = ""): Seq[Row] = {
    val rows = (0 until productArity).iterator.toSeq.flatMap { i =>
      val name = prefix + productElementName(i)
      productElement(i) match {
        case counted:CountedThings[_] =>
          counted.rows
        case None =>
          if (includeNone)
            Seq(Row(name, ""))
          else
            Seq.empty
        case Some(a) =>
          Seq(Row(name, a))
        case rs: RowsSource =>
          rs.toRows()
        case seq: Seq[Any] =>
          seq.map(Row(name, _))
        case nonOption =>
          Seq(Row(name, nonOption))
      }
    }
    rows
  }
}




trait RowSource {
  def toRow: Row
}

