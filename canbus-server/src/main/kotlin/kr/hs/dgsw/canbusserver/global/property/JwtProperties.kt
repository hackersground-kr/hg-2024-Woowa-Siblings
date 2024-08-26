package kr.hs.dgsw.canbusserver.global.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("jwt.security")
class JwtProperties(
    val accessKey: String,
    val refreshKey: String,
    val accessExpiredDate: String,
    val refreshExpiredDate: String
)