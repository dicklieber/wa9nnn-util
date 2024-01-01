package com.wa9nnn.wa9nnnutil


class OsDetectorSpec extends UtilSpec {

  "OsDetectorSpec" should {
    "osName" in {
      OsDetector.windows mustBe false
      OsDetector.macos mustBe true // util must be built on MacOs.
      OsDetector.linux mustBe false
    }
  }
}
