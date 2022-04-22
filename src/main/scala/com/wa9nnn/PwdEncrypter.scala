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
package com.wa9nnn

import com.typesafe.scalalogging.LazyLogging
import com.wa9nnn.PwdEncrypter.{charset, prefix, prefixLength}

import java.nio.charset.Charset
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

/**
 * This is useful for passwords at rest, where hashes are not appropriate.
 * @param secretKey usually something for a given application.
 */
class PwdEncrypter(secretKey: String) extends LazyLogging {
  private val keySpec = new SecretKeySpec(secretKey.getBytes(), "Blowfish")

  def encrypt(plainText: String): String = {
    import javax.crypto.Cipher
    import java.util.Base64


    val cipher = Cipher.getInstance("Blowfish")
    cipher.init(Cipher.ENCRYPT_MODE, keySpec)
    val bytes = cipher.doFinal(plainText.getBytes(charset))
    logger.trace("encrpt: encrypted bytes: {}", bytes)


    val encryptedtext = Base64.getEncoder.encodeToString(bytes)
    s"$prefix$encryptedtext"
  }

  def decrypt(maybeEncrypted: String): String = {
    def doDecrypt(reallyEncrypted: String): String = {
      val bytes = Base64.getDecoder.decode(reallyEncrypted)

      logger.trace("decrypt: encrypted bytes: {}", bytes)
      val cipher = Cipher.getInstance("Blowfish")
      cipher.init(Cipher.DECRYPT_MODE, keySpec)
      val decrypted = cipher.doFinal(bytes)


      val str = new String(decrypted, charset)
      logger.trace("decrypt: decrypoted: {}", str)
      str
    }

    if (maybeEncrypted startsWith prefix)
      doDecrypt(maybeEncrypted.drop(prefixLength))
    else
      maybeEncrypted
  }
}

object PwdEncrypter {
  val prefix: String = "{b}:"
  val prefixLength: Int = prefix.length
  val charset: Charset = Charset.forName("UTF-8")

}
