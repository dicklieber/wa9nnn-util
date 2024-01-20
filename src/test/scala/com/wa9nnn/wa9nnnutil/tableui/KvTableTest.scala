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

class KvTableTest extends UtilSpec {

  "KvTableTest" should {
    "with header" in {
      val table: Table = KvTable("Header",
        "kv" -> "kv value",
        KvTableSection("SectionHeader",
          Row("row0", 0),
          "row1" -> 1
        )
      )

      table.cssClass.head mustBe "headeredTable"
      val headCell: Cell = table.headers.head.head
      headCell.value mustBe ("Header")
      headCell.colSpan mustBe (2)
      val rows: Seq[Row] = table.rows
      rows must have length 4
    }
    "without header" in {
      val table: Table = KvTable(
        "kv" -> "kv value",
        KvTableSection("SectionHeader",
          Row("row0", 0),
          "row1" -> 1
        )
      )

      table.cssClass.head mustBe "headeredTable"
      table.headers mustBe empty

      val rows: Seq[Row] = table.rows
      rows must have length 4
    }

  }
}
