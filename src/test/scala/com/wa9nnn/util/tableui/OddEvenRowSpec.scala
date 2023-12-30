package com.wa9nnn.util.tableui

import com.wa9nnn.util.UtilSpec


class OddEvenRowSpec extends UtilSpec {

  "OddEvenRowSpec" should {
    val oer = new OddEvenRow()
    "proper sequence string" in {
      oer.nextRowClass must equal("odd")
      oer.nextRowClass must equal("even")
      oer.nextRowCssClass.cssClass must equal("odd")
      oer.nextRowCssClass.cssClass must equal("even")
      oer.nextRowClass must equal("odd")
      oer.nextRowCssClass.cssClass must equal("even")
    }
  }
}
