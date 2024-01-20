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


/**
 * Something that can be rendered as an html <table> using the play Whirl template /app/views/renderTable.scala.html
 *
 * @param headers  zero or more rows of column headers [[Cell]]s that makeup the <thead>
 * @param rows     in the <tbody>
 * @param cssClass class of table element.
 */
case class Table(headers: Seq[Seq[Cell]], rows: Seq[Row], id: Option[String] = None, cssClass: Seq[String] = Seq("headeredTable")) {

  lazy val cols: Int = headers
    .lastOption // are there headers
    .map(_.length) // how long is the last one.
    .getOrElse(0) // 0 if not header
  val columnHeaders: Seq[Seq[Cell]] = headers.map(hw => hw.map(Cell(_)))

  def withId(id: String): Table = copy(id = Option(id))

  def withCssClass(css: String): Table = copy(cssClass = cssClass.appended(css))


  def append(newRows: Seq[Row]): Table =
    copy(rows =
      rows.appendedAll(newRows)
    )

  def append(tableSection: TableSection): Table =
    copy(rows =
      rows.appendedAll(tableSection.rows)
    )

  //  /**
  //   * Add rows to this table with a header
  //   * This may not correctly set the colspan of the section headers corredtly it the lat row o the header '
  //   * doesn't match the length of [[Cell]]s in the new [[Rows]]s.
  //   *
  //   * @param sectionName full with <td class="sectionHeader> element.
  //   * @param newRows     to follow the section header.
  //   * @return a new [[Table]].
  //   */
  //  def appendSection(tableSection: TableSection): Table =
  //    val sectionHeaderRow = Row(Seq(Cell(tableSection.sectionName)
  //      .withCssClass("sectionHeader")
  //      .withColSpan(cols))
  //    )
  //    copy(rows =
  //      rows.appendedAll(tableSection.rows)
  //    )
}


object Table {
  /**
   * Build an empty table with tjust a single line header.
   *
   * @param header
   * @return table with this a header.
   */
  def empty(header: String): Table =
    new Table(Seq(Seq(Cell(header))), Seq.empty, cssClass = Seq("emptyTable"))

  /**
   * A single row of column headers
   * Note because of Java Type erasure this ctor can't also use [[Seq]] for the headers, so [[List]] allows
   * the scala compiler to differentiate between the main ctor and the auxiliary ctor.
   *
   * This allows a vararg for rows to enable a less cluttered API e.g.
   * {{
   * new UiTable(
   * Header("A", "B", "C"),
   * UiRow("a", "b", "c")),
   * UiRow("aa", "bb", "cc"))
   * }}
   *
   * @param columnHeaders Either a [[List[TableCell]] or any other value that [[Cell]] can accept.
   * @param rows          <tbody>
   */
  def apply(columnHeaders: List[Any], rows: Row*): Table = {
    Table(Seq(columnHeaders.map(Cell(_))), rows)
  }

  /**
   * A table with one column.
   *
   * @param singleColHeader column header
   * @param rowValues       values for row.
   * @return
   */
  def apply(singleColHeader: String, rowValues: Seq[Any]): Table = {
    val rows: Seq[Row] = rowValues.map { v =>
      Row(Seq(Cell(v)))
    }
    Table(Header(singleColHeader), rows)

  }

  /**
   * A multi line header
   *
   * {{
   * Table(
   * Header("fullspan", "colA", "colB", "colC"),
   * Row("a", "b", "c")),
   * Row("aa", "bb", "cc"))
   * }}
   *
   * @param header a [[Header]]
   * @param rows   <tbody>
   */
  def apply(header: Header, rows: Seq[RowSectionOrKV]): Table = {
    val rowBuilder = Seq.newBuilder[Row]
    rows.foreach {
      case r: Row =>
        rowBuilder += r

      case (name: String, value: Any) =>
        rowBuilder += Row(Seq(Cell(name), Cell(value)))

      case t: TableSection =>
        rowBuilder ++= t.rows
    }

    Table(header.rows, rowBuilder.result())
  }

  type RowSectionOrKV = Row | TableSection | (String, Any)
}