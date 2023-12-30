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

import play.api.libs.json.Json

class HostAndPortSpec extends UtilSpec {

  "HostAndPort" should {
    "handle host with port" in {
      val hostAndPort = HostAndPort("www.u505.com:123", 80)
      hostAndPort.host must equal ("www.u505.com")
      hostAndPort.port must equal (123)
    }
    "handle host with default port" in {
      val hostAndPort = HostAndPort("www.u505.com", 80)
      hostAndPort.host must equal ("www.u505.com")
      hostAndPort.port must equal (80)
    }
    "fail with non-numeric port" in {
      an [IllegalArgumentException] should be thrownBy HostAndPort("www.u505.com:crap", 80)
    }
    "fail with empty string" in {
      an [IllegalArgumentException] should be thrownBy HostAndPort("", 80)
    }

    "implicit toString" in {
      import com.wa9nnn.util.HostAndPort.hostAndPortToString
      val hostAndPort: HostAndPort = HostAndPort("www.u505.com:123", 80)
      val s: String = hostAndPort
      s must equal ("www.u505.com:123")
    }

    "socketaddress" in {
      val hostAndPort: HostAndPort = HostAndPort("www.u505.com:123", 80)
      val address = hostAndPort.toSocketAddress
      address.getPort must equal (123)
      address.getHostString must equal ("www.u505.com")
    }

    "json" in {
      import com.wa9nnn.util.HostAndPort.hostAndPortFormat
      val hostAndPort: HostAndPort = HostAndPort("www.u505.com", 80)
      val pretty = Json.prettyPrint(Json.toJson(hostAndPort))
      pretty must equal (""""www.u505.com:80"""")
      val backAgain = Json.parse(pretty).as[HostAndPort]
      backAgain must equal (hostAndPort)
    }
  }
}
