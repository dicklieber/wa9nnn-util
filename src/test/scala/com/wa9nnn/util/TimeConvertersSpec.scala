package com.wa9nnn.util

import org.specs2.mutable.Specification

import java.time.{Duration, Instant, ZoneId}
import com.wa9nnn.util.TimeConverters._

import java.util.TimeZone
class TimeConvertersSpec extends Specification {

  "TimeConvertersSpec" should {
    val fixedInstant = Instant.ofEpochSecond(10000)

    "durationToString" in {
      val duration: Duration = Duration.ofDays(1)
      val sDuration: String = durationToString(duration)
      sDuration must beEqualTo  ("1 day 0 hour")
    }

    "parseInstant" in {
      val string: String = fixedInstant.toString
      val backAgain: Instant = stringToInstant(string)
      backAgain must beEqualTo (fixedInstant)
    }

    "UTCLocal" in {
      val s = instantDisplayUTCLocal(fixedInstant, TimeZone.getTimeZone("CST").toZoneId)
      s must beEqualTo("01/01/70 02:46 UTC (20:46 CST)")
    }

    "fileStamp" >> {
      fileStamp(fixedInstant) must beEqualTo ("1970-01-01 024640UTC")
    }
  }
}
