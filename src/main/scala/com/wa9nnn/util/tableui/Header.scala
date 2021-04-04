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

package com.wa9nnn.util.tableui

import scala.reflect.{ClassTag, classTag}


/**
 * Builds a Seq[Seq[TableCell
 * use apply methods for useful construction.
 *
 * @param rows header rows.
 */
case class Header(rows: Seq[Seq[Cell]])

object Header {
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

}