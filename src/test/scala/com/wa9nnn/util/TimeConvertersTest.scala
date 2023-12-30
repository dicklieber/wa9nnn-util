package com.wa9nnn.util


import java.time.Instant
import java.util.TimeZone
import scala.concurrent.duration.Duration
import TimeConverters.{fileStamp, instantDisplayUTCLocal}
import DurationHelpers.given

class TimeConvertersTest extends UtilSpec {

  "TimeConvertersSpec" should {
    val fixedInstant = Instant.ofEpochSecond(10000)

    "durationToString" in {
      val duration: Duration = Duration(1, "day")
      val sDuration: String = duration
      sDuration must equal("1 day 0 hour")
    }

    //    "parseInstant" in {
    //      val string: String = fixedInstant.toString
    //      val backAgain: Instant = stringToInstant(string)
    //      backAgain mustBe equal (fixedInstant)
    //    }

    "UTCLocal" in {
      val s = instantDisplayUTCLocal(fixedInstant, TimeZone.getTimeZone("CST").toZoneId)
      s must equal("01/01/70 02:46 UTC (20:46 CST)")
    }

    "fileStamp" in {
      fileStamp(fixedInstant) must equal("1970-01-01 024640UTC")
    }
  }
}
