package com.wa9nnn.util

import org.specs2.mutable.Specification

class OsDetectorSpec extends Specification {

  "OsDetectorSpec" should {
    "osName" in {
      OsDetector.windows must beFalse
      OsDetector.macos must beTrue // util must be built on MacOs.
      OsDetector.linux must beFalse
    }
  }
}
