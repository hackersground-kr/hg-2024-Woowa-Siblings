package kr.hs.dgsw.canbusserver.global.config

import kr.hs.dgsw.canbusserver.global.property.JwtProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(
    JwtProperties::class
)
class PropertiesConfig