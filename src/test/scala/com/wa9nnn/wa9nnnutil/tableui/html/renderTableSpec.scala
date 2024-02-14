/*
 * Copyright (C) 2021 Dick Lieber, WA9NNN
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

package com.wa9nnn.wa9nnnutil.tableui.html

import com.wa9nnn.wa9nnnutil.UtilSpec
import com.wa9nnn.wa9nnnutil.tableui.Header.singleRow
import com.wa9nnn.wa9nnnutil.tableui.{Cell, Header, KvTableSection, Row, Table, TableRenderer, TableSection}

import java.nio.file.{Files, Paths}
import java.time.Instant

class renderTableSpec extends UtilSpec {

  "renderTableSpec" should {
    import com.wa9nnn.wa9nnnutil.tableui.Header.s2cell
    val header = Header("Overall", "Alpha", "Beta", "Charlie")

    val rows = Seq(
      Row("Row1", "a string", Cell(Instant.EPOCH))
    )

    val table = Table(header, rows)

    "render" in {

      val html = TableRenderer(table)
      html must equal("""<table class="headeredTable">
                            | <thead>
                            |  <tr>
                            |   <th colspan="3" class="sorter-false">Overall </th>
                            |  </tr>
                            |  <tr>
                            |   <th>Alpha </th>
                            |   <th>Beta </th>
                            |   <th>Charlie </th>
                            |  </tr>
                            | </thead>
                            | <tbody>
                            |  <tr>
                            |   <td> Row1 </td>
                            |   <td> a string </td>
                            |   <td class="number"> 01/01/70 00:00 UTC (18:00 CST) </td>
                            |  </tr>
                            | </tbody>
                            |</table>""".stripMargin.replaceAll("\n" , " \n"))
    }
    "append section" in {
      val ts = KvTableSection("New Section", Row.ofAny("newRow0","newRow1","newRow2"))
      val appendedTable: Table = table.append(ts)
      val html: String = TableRenderer(appendedTable)
      html must equal(
        """<table class="headeredTable">
          | <thead>
          |  <tr>
          |   <th colspan="3" class="sorter-false">Overall </th>
          |  </tr>
          |  <tr>
          |   <th>Alpha </th>
          |   <th>Beta </th>
          |   <th>Charlie </th>
          |  </tr>
          | </thead>
          | <tbody>
          |  <tr>
          |   <td> Row1 </td>
          |   <td> a string </td>
          |   <td class="number"> 01/01/70 00:00 UTC (18:00 CST) </td>
          |  </tr>
          |  <tr>
          |   <td class="sectionHeader" colspan="2"> New Section </td>
          |  </tr>
          |  <tr>
          |   <td> newRow0 </td>
          |   <td> newRow1 </td>
          |   <td> newRow2 </td>
          |  </tr>
          | </tbody>
          |</table>""".stripMargin.replaceAll("\n", " \n"))

    }
  }
}
