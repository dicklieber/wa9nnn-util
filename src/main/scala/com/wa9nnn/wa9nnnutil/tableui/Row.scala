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

/**
 * An html table row, <tr>.
 *
 * @param cells      cells for the row.
 * @param rowId      if Some, this is the id attribute of the <tr>
 * @param cssClass   css classes for the row.
 * @param rowToolTip tool tip. Non-empty string will become a tooltip.
 */
case class Row(cells: Seq[Cell],
               rowId: Option[String] = None,
               cssClass: Seq[String] = Seq.empty,
               rowToolTip: Option[String] = None)
  extends RowSource {
  def withToolTip(toolTip: String): Row = copy(rowToolTip = Option(toolTip))

  /**
   * Append this cssClass to any exiting classes.
   *
   * @param cssClass to be appended.
   * @return new Row with additional cssClass.
   */
  def withCssClass(cssClass: String): Row = copy(cssClass = this.cssClass :+ cssClass)

  /**
   * Append a cell to this row.
   *
   * @param in to be appended. If this is a [[Cell]] it is appended.
   *           If any other type, a new [[Cell]] will be created with the value.
   * @return a new UiRow with additional cell.
   */
  def :+(in: Any): Row = {
    copy(cells = cells :+ Cell(in))
  }

  /**
   * Append a cell to this row if not None
   *
   * @param maybeIn to be appended. If this is a [[Cell]] it is appended.
   *                If any other type, a new [[Cell]] will be created with the value.
   * @return a new UiRow with additional cell.
   */
  def addOption(maybeIn: Option[Any]): Row = {
    maybeIn match {
      case Some(value: Any) =>
        copy(cells = cells :+ Cell(value))
      case None =>
        this
    }
  }

  /**
   * @return all the cssClasses separated by a space.
   */
  def renderedCssClass: String = {
    cssClass.mkString(" ")
  }

  def withId(id: String): Row = copy(rowId = Some(id))

  override def toRow: Row = this
}

object Row {
  /**
   *
   * @param rowHeader text for leftmost column in row.
   * @param values    values for subsequent cells in the row.
   * @return a Row.
   */
  def apply(rowHeader: String, values: Any*): Row = {
    val headerCell = Cell(rowHeader)
    val subsiquentCells = values.map(Cell(_))
    Row(headerCell +: subsiquentCells)
  }

  def apply(t:Tuple2[String, Any]): Row =
    Row(t._1, t._2)

  /**
   *
   * @param headerCell  leftmost column in row.
   * @param values      values for subsiquent cells in the row.
   * @return
   */
  def apply(headerCell: Cell, values: Any*): Row = {
    val subsiquentCells = values.map(Cell(_))
    Row(headerCell +: subsiquentCells)
  }

  /**
   * Works for any case class assuming types are understood by [[Cell]] for complex i.e. parameters that are other case classes this will
   * make [[Cell]]s that just invoke toString, which may not be what you want.
   * Usually you'll want to implement your own method on the case class thqt returns a UiRow so you have full control over order.
   * If you use this use [[Header[T]]] to build a header to make a UiTable
   *
   * @param anyCaseClass case classes automatically impement [[Product]]
   * @return
   */
  def apply(anyCaseClass: Product): Row = {
    val r: Seq[Cell] = anyCaseClass.productIterator.map(Cell(_)).toSeq
    new Row(r)
  }

  def ofAny(any: Any*): Row = {
    Row(
      any.map(Cell(_))
    )
  }
}