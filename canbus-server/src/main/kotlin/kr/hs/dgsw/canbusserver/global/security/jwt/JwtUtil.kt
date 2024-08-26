package kr.hs.dgsw.canbusserver.global.security.jwt

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import kr.hs.dgsw.canbusserver.domain.auth.exception.UserNotFoundException
import kr.hs.dgsw.canbusserver.domain.user.User
import kr.hs.dgsw.canbusserver.domain.user.UserRepository
import kr.hs.dgsw.canbusserver.global.property.JwtProperties
import kr.hs.dgsw.canbusserver.global.security.jwt.exception.ExpiredTokenException
import kr.hs.dgsw.canbusserver.global.security.jwt.exception.InvalidTokenException
import kr.hs.dgsw.canbusserver.global.security.jwt.exception.SignatureNotMatchException
import kr.hs.dgsw.canbusserver.global.security.jwt.exception.TokenNotProvidedException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtUtil(
    private val userRepository: UserRepository,
    private val jwtProperties: JwtProperties,
) {

    private val signatureAlgorithm = SignatureAlgorithm.HS256

    private fun create(user: User, type: TokenType): String {

        val claimsMap = mapOf("id" to user.id)
        val expiredDate = Calendar.getInstance().apply { time = Date() }

        when (type) {
            TokenType.ACCESS -> expiredDate.add(Calendar.DATE, jwtProperties.accessExpiredDate.toInt())
            TokenType.REFRESH -> expiredDate.add(Calendar.DATE, jwtProperties.refreshExpiredDate.toInt())
        }

        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setHeaderParam("alg", signatureAlgorithm)
            .setClaims(claimsMap)
            .setExpiration(expiredDate.time)
            .signWith(getSecretKey(type), signatureAlgorithm)
            .compact()
    }

    fun refresh(refreshToken: String): String {

        val claims = validateToken(refreshToken, TokenType.REFRESH)

        val id = claims["id"].toString().toLong()
        val user = userRepository.findByIdOrNull(id) ?: throw UserNotFoundException()

        return createAccessToken(user)
    }

    fun createAccessToken(user: User): String {
        return create(user, TokenType.ACCESS)
    }

    fun createRefreshToken(user: User): String {
        return create(user, TokenType.REFRESH)
    }

    fun getIdByAccessToken(accessToken: String): Long {

        val claims = validateToken(accessToken, TokenType.ACCESS)

        return claims["id"].toString().toLong()
    }

    private fun validateToken(token: String, type: TokenType): Claims {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(getSecretKey(type))
                .build()
                .parseClaimsJws(token).body
        } catch (e: ExpiredJwtException) {
            throw ExpiredTokenException()
        } catch (e: IllegalArgumentException) {
            throw TokenNotProvidedException()
        } catch (e: UnsupportedJwtException) {
            throw InvalidTokenException()
        } catch (e: MalformedJwtException) {
            throw InvalidTokenException()
        } catch (e: SignatureException) {
            throw SignatureNotMatchException()
        }
    }

    private fun getSecretKey(type: TokenType): SecretKey? {
        val secretKey = when (type) {
            TokenType.ACCESS -> jwtProperties.accessKey
            TokenType.REFRESH -> jwtProperties.refreshKey
        }
        return Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8))
    }

}