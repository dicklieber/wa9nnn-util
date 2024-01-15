/*
 * Copyright (C) 2024 Dick Lieber, WA9NNN
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

case class TableSection(sectionName: String, newRows: Seq[Row]):
  def toRows: Seq[Row] = {
    val cols = newRows.headOption.map(_.cells.length).getOrElse(1)
    val sectionHeaderRow = Cell(sectionName).withColSpan(cols)
    Row(Seq(sectionHeaderRow)) +: newRows
  }

object TableSection:
  /**
   * Create a new [[TableSection]] using rows or [[Tuple2]]
   *
   * @param sectionName header for section
   * @param rows        [[Row]] or "Name" -> 42
   */
  def create(sectionName: String, rows: RowOrKV*): TableSection =
    TableSection(sectionName, rows.map {
      case r: Row =>
        r
      case (name: String, value: Any) =>
        Row(name, value)
    }
    )
  type RowOrKV = Row | (String, Any)