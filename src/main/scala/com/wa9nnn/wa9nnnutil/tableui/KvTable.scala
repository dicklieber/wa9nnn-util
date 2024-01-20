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

object KvTable:
  /**
   * Simple table with two columns and a header.
   *
   * @param header top line <th>
   * @param kv     any number of (String,Any) or [[TableSection]]s.
   * @return the table
   */
  def apply(header: String, kv: (TableSection | (String, Any) | Row)*): Table =
    val builder = Seq.newBuilder[Row]
    kv.foreach {
      case tableSection: TableSection =>
        builder ++= tableSection.rows

      case row: Row =>
        builder += row

      case (name: String, value: Any) =>
        builder += Row.ofAny(name, value)
    }

    Table(Seq(Seq(Cell(header).withColSpan(2))), builder.result())

  /**
   * Simple table with two columns and no header.
   *
   * @param kv     any number of (String,Any) or [[TableSection]]s.
   * @return the table
   */
  def apply(kv: TableSection | (String, Any)*): Table =

    val builder = Seq.newBuilder[Row]
    kv.foreach {
      case tableSection: TableSection =>
        builder ++= tableSection.rows

      case (name: String, value: Any) =>
        builder += Row.ofAny(name, value)
    }

    Table(Seq.empty, builder.result())
