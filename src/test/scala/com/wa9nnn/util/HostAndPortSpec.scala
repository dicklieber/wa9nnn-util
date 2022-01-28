/*
 * Copyright (C) 2022 Dick Lieber, WA9NNN
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.wa9nnn.util

import org.specs2.mutable.Specification

class HostAndPortSpec extends Specification {

  "HostAndPort" should {
    "handle host with port" in {
      val hostAndPort = HostAndPort("www.u505.com:123", 80)
      hostAndPort.host must beEqualTo ("www.u505.com")
      hostAndPort.port must beEqualTo (123)
    }
    "handle host with default port" in {
      val hostAndPort = HostAndPort("www.u505.com", 80)
      hostAndPort.host must beEqualTo ("www.u505.com")
      hostAndPort.port must beEqualTo (80)
    }
    "fail with non-numeric port" in {
      HostAndPort("www.u505.com:crap", 80) must throwAn[IllegalArgumentException]
    }
    "fail with empty string" in {
      HostAndPort("", 80) must throwAn[IllegalArgumentException]
    }

    "implicit toString" in {
      import com.wa9nnn.util.HostAndPort.hostAndPortToString
      val hostAndPort: HostAndPort = HostAndPort("www.u505.com:123", 80)
      val s:String = hostAndPort
      s must beEqualTo ("www.u505.com:123")

    }
  }
}
