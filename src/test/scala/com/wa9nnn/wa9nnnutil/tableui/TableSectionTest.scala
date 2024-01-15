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

import com.wa9nnn.wa9nnnutil.UtilSpec

class TableSectionTest extends UtilSpec {

  "TableSectionTest" should {
    "create" when {
      val tableSection = TableSection.create("name",
        Row.ofAny("hh", "val"),
        Row.ofAny("hh", 42),
        "kv" -> 23.00
      )
      val rows = tableSection.toRows
      "header" in {
        val headerCell = rows.head.cells.head
        headerCell.value mustBe ("name")
        headerCell.colSpan mustBe(2)
      }
      "row0" in {
        val row0: Row = rows(1)
        row0.cells(0).value mustBe ("hh")
        row0.cells(1).value mustBe ("val")
      }
    }
  }
}

