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
 *
 *
 */

package com.wa9nnn.wa9nnnutil.tableui

import scala.language.implicitConversions


/**
 * Builds a Seq[Seq[TableCell
 * use apply methods for useful construction.
 *
 * @param rows header rows.
 */
case class Header(rows: Seq[Seq[Cell]] = Seq.empty) {
  def append(headerRow:Seq[Any]):Header = {
    copy(rows = rows.appended(headerRow.map(Cell(_))))
  }
}

object Header {
  def singleRow(cols:Any*) :Header = {
    new Header(Seq(cols.map(Cell(_))))
  }
  /**
   *
   * @param allColHeader top row header that will span all subheaders
   * @param subheaders   subheaders.
   * @return
   */
  def apply(allColHeader: String, subheaders: Any*): Header = {
    val topHeader = Cell(allColHeader).withColSpan(subheaders.length)
    Header(Seq(
      Seq(Cell(topHeader)
        .withCssClass("sorter-false")),
      subheaders.map(Cell(_))
    ))
  }


  implicit def s2cell(s:String):Cell = Cell(s)

}