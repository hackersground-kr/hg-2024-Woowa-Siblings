package kr.hs.dgsw.canbusserver.global.config

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@SecurityScheme(
    name = "Bearer Token",
    type = SecuritySchemeType.HTTP,
    `in` = SecuritySchemeIn.HEADER,
    bearerFormat = "JWT",
    scheme = "bearer"
)
@Configuration
class SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI {

        val securityRequirement = SecurityRequirement()
            .addList("Bearer Token")

        val info = Info()
            .title("Canbus API Docs")
            .version("v1.0.0")
            .description("Canbus API 명세서")

        return OpenAPI()
            .components(Components())
            .addSecurityItem(securityRequirement)
            .info(info)
    }

}