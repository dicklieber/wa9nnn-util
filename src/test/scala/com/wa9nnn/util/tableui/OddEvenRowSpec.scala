package com.wa9nnn.util.tableui

import org.specs2.mutable.Specification

class OddEvenRowSpec extends Specification {

  "OddEvenRowSpec" should {
    val oer = new OddEvenRow()
    "proper sequence string" in {
      oer.nextRowClass must be equalTo "odd"
      oer.nextRowClass must be equalTo "even"
      oer.nextRowCssClass.cssClass must be equalTo "odd"
      oer.nextRowCssClass.cssClass must be equalTo "even"
      oer.nextRowClass must be equalTo "odd"
      oer.nextRowCssClass.cssClass must be equalTo "even"
    }
  }
}
