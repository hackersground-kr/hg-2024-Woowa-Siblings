package kr.hs.dgsw.canbusserver.global.encrypt

import org.springframework.stereotype.Component
import java.security.MessageDigest

@Component
class EncryptUtil {

    private val algorithm = "SHA-512"

    fun encode(password: String): String {
        return try {
            MessageDigest.getInstance(algorithm)
                .digest(password.toByteArray())
                .joinToString("") { "%02x".format(it) }
        } catch (e: Exception) {
            throw RuntimeException()
        }
    }

    fun match(originPassword: String, encryptedPassword: String): Boolean {
        return encryptedPassword == encode(originPassword)
    }

}