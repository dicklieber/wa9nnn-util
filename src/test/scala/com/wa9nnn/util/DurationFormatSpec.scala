package com.wa9nnn.util

import org.specs2.mutable.Specification

import java.time.{Duration, Instant}

class DurationFormatSpec extends Specification {

  "DurationFormatSpec" >> {
    "Happy" >> {
      com.wa9nnn.util.DurationFormat(Duration.ofSeconds( 25)) must beEqualTo ("25 sec 0 ms")
      com.wa9nnn.util.DurationFormat(Duration.ofMinutes( 5)) must beEqualTo ("5 min 0 sec")
      com.wa9nnn.util.DurationFormat(Duration.ofHours( 5)) must beEqualTo ("5 hours 0 min")
      com.wa9nnn.util.DurationFormat(Duration.ofDays( 5)) must beEqualTo ("5 day 0 hour")
      com.wa9nnn.util.DurationFormat(Duration.ofDays( 300)) must beEqualTo ("300 day 0 hour")
      com.wa9nnn.util.DurationFormat(Duration.ofDays( 300).plusMinutes(25)) must beEqualTo ("300 day 0 hour 25 min")
    }
    "Instant" >> {
      val before: Instant = Instant.EPOCH
      val  now = before.plus(Duration.ofHours(1).abs())
      com.wa9nnn.util.DurationFormat(before, now) must beEqualTo ("1 hours 0 min")
    }
  }
}
