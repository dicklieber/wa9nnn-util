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

package com.wa9nnn.util.tableui

class SectionedRowCollector(val rows: Seq[Row] = Seq.empty) {

  def +=(seperator: String, rows: Seq[Row]): SectionedRowCollector = {

    val sectionHeader = Row(Seq(Cell(seperator)
      .withCssClass("sectionHeader")
      .withColSpan(rows.length)
    ))


    new SectionedRowCollector(this.rows :+ sectionHeader :++ rows)
  }

  def +=(seperator: Any, columns: Seq[Any], rows: Seq[Row]): SectionedRowCollector = {


    val colHeaders: Row = Row(columns.map {
      Cell(_)
    }).withCssClass("sectionHeader")

    val cols = colHeaders.cells.foldLeft(0){case (accum, cell) => accum + cell.colSpan}
    val sectionHeader = Row(Seq(Cell(seperator)
      .withCssClass("sectionHeader")
      .withColSpan(cols)
    ))

    new SectionedRowCollector(this.rows :+ sectionHeader :+ colHeaders :++ rows)
  }
  def += (sectionRows:SectionedRows):SectionedRowCollector = {

    val colHeaders: Row = Row(sectionRows.columns.map {
      Cell(_)
    }).withCssClass("sectionHeader")

    val cols = colHeaders.cells.foldLeft(0){case (accum, cell) => accum + cell.colSpan}
    val sectionHeader = Row(Seq(Cell(sectionRows.sectionName)
      .withCssClass("sectionHeader")
      .withColSpan(cols)
    ))

    new SectionedRowCollector(this.rows :+ sectionHeader :+ colHeaders :++ rows)

  }
}

case class SectionedRows(sectionName:String, columns: Any*)(rows:Seq[Row])