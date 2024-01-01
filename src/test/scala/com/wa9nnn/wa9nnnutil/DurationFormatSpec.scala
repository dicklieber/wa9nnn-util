package com.wa9nnn.wa9nnnutil


import com.wa9nnn.wa9nnnutil.DurationHelpers.given
import org.scalatest.prop.TableDrivenPropertyChecks.*
import org.scalatest.prop.{TableDrivenPropertyChecks, TableFor2}

import scala.concurrent.duration.Duration
import scala.language.postfixOps

class DurationFormatTest extends UtilSpec with TableDrivenPropertyChecks {


  val useCases: TableFor2[Duration, String] =
    Table[Duration, String](
      ("duration", "string"), // First tuple defines column names
      (Duration("25 seconds"), "25 sec 0 ms"), // Subsequent tuples define the data
      (Duration("5 hours"), "5 hours 0 min"),
      (Duration("5 days"), "5 day 0 hour"),
      (Duration("300 days"), "300 day 0 hour"),
      (Duration("300 days").plus(Duration(25, "minutes")), "300 day 0 hour 25 min"),
    )

  forAll(useCases) { (duration, expected) =>
    val s: String = duration
    s mustBe expected
  }

  //    "Happy" in {
  //      Duration("25 seconds") must equal("25 sec 0 ms")
  //      //      com.wa9nnn.util.DurationFormat(Duration.ofMinutes(5)) must equal("5 min 0 sec")
  //      //      com.wa9nnn.util.DurationFormat(Duration.ofHours(5)) must equal("5 hours 0 min")
  //      //      com.wa9nnn.util.DurationFormat(Duration.ofDays(5)) must equal("5 day 0 hour")
  //      //      com.wa9nnn.util.DurationFormat(Duration.ofDays(300)) must equal("300 day 0 hour")
  //      //      com.wa9nnn.util.DurationFormat(Duration.ofDays(300).plusMinutes(25)) must equal("300 day 0 hour 25 min")
  //    }
  //    "Instant" in {
  //      val before: Instant = Instant.EPOCH
  //      val now = before.plus(1, ChronoUnit.MILLIS)
  //      com.wa9nnn.util.DurationHelpers.between(before, now) must equal("1 hours 0 min")
  //    }
}
