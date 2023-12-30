package com.wa9nnn.util


class OsDetectorSpec extends UtilSpec {

  "OsDetectorSpec" should {
    "osName" in {
      OsDetector.windows mustBe false
      OsDetector.macos mustBe true // util must be built on MacOs.
      OsDetector.linux mustBe false
    }
  }
}
