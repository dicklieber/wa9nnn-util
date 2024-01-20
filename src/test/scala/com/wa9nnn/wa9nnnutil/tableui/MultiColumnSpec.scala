
package com.wa9nnn.wa9nnnutil.tableui

import com.wa9nnn.wa9nnnutil.{UtilSpec, tableui}


class MultiColumnSpec extends UtilSpec  {

  val expected100: String =
    """0 10 20 30 40 50 60 70 80 90
      |1 11 21 31 41 51 61 71 81 91
      |2 12 22 32 42 52 62 72 82 92
      |3 13 23 33 43 53 63 73 83 93
      |4 14 24 34 44 54 64 74 84 94
      |5 15 25 35 45 55 65 75 85 95
      |6 16 26 36 46 56 66 76 86 96
      |7 17 27 37 47 57 67 77 87 97
      |8 18 28 38 48 58 68 78 88 98
      |9 19 29 39 49 59 69 79 89 99""".stripMargin
  val expected99: String =
    """0 10 20 30 40 50 60 70 80 90
      |1 11 21 31 41 51 61 71 81 91
      |2 12 22 32 42 52 62 72 82 92
      |3 13 23 33 43 53 63 73 83 93
      |4 14 24 34 44 54 64 74 84 94
      |5 15 25 35 45 55 65 75 85 95
      |6 16 26 36 46 56 66 76 86 96
      |7 17 27 37 47 57 67 77 87 97
      |8 18 28 38 48 58 68 78 88 98
      |9 19 29 39 49 59 69 79 89 -""".stripMargin


  "Organize" should {
    "0 items" in {
      gridCheck(0, "")
    }
    "1 item" in {
      gridCheck(1, "0")
    }
    "2 item" in {
      gridCheck(2,
        """0
          |1""".stripMargin)
    }
    "99 items" in {
      gridCheck(99, expected99)
    }
    "100 items" in {
      gridCheck(100, expected100)
    }
    "101 items" in {
      gridCheck(101,
        """0 11 22 33 44 55 66 77 88 99
          |1 12 23 34 45 56 67 78 89 100
          |2 13 24 35 46 57 68 79 90 -
          |3 14 25 36 47 58 69 80 91 -
          |4 15 26 37 48 59 70 81 92 -
          |5 16 27 38 49 60 71 82 93 -
          |6 17 28 39 50 61 72 83 94 -
          |7 18 29 40 51 62 73 84 95 -
          |8 19 30 41 52 63 74 85 96 -
          |9 20 31 42 53 64 75 86 97 -
          |10 21 32 43 54 65 76 87 98 -""".stripMargin)
    }

  }

  "Table" should {
    "create Table" in {
      val items: Seq[Cell] = Range(0, 99).map(Cell(_))
      val header = "Header"
      val table: tableui.Table = MultiColumn(items, 10, header)
      table.headers must have length 1
      val headerRow0 = table.headers.head
      headerRow0 must have length 1
      val headerCell = headerRow0.head.asInstanceOf[Cell]
      headerCell.colSpan must equal(10)

      val rows: Seq[Row] = table.rows
      val lastCell: Cell = rows.last.cells.last
      lastCell.value must equal("")
    }
    "create Table noHeader" in {
      val items: Seq[Cell] = Range(0, 99).map(Cell(_))
      val header = "Header"
      val table: tableui.Table = MultiColumn(items, 10)
      table.headers must have size(0)
    }
    "empty items" in {
      val table: tableui.Table = MultiColumn(Seq.empty, 10, noDataMessage = "empty")
      table.rows must have size(1)
      val uiRow: Row = table.rows.head
      uiRow.cells must have size(1)
      uiRow.cells.head.value must equal("empty")
    }
  }

  def gridCheck(nItems: Int, expected: String):Unit = {
    val items = Range(0, nItems).map(Cell(_))

    val organized: Seq[Seq[Any]] = MultiColumn.organize(items, 10)
    val grid: String = organized.map { row ⇒
      row.map(_.toString).mkString(" ")
    }.mkString("\n")
    val equals: Boolean = grid == expected
    println(s"nItems: $nItems grid: $grid expected: $expected  eq: $equals  $$$$\n")
//    val r: MatchResult[String] = grid must equal(expected)
    grid must equal("grid")

  }
}
