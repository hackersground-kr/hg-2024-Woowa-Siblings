package kr.hs.dgsw.canbusserver.global.security

import kr.hs.dgsw.canbusserver.global.filter.ExceptionFilter
import kr.hs.dgsw.canbusserver.global.filter.JwtFilter
import kr.hs.dgsw.canbusserver.global.security.handler.CustomAccessDeniedHandler
import kr.hs.dgsw.canbusserver.global.security.handler.CustomAuthenticationEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtFilter: JwtFilter,
    private val exceptionFilter: ExceptionFilter,
    private val customAuthenticationEntryPoint: CustomAuthenticationEntryPoint,
    private val customAccessDeniedHandler: CustomAccessDeniedHandler
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .httpBasic { it.disable() }
            .csrf { it.disable() }
            .cors {
                it.configurationSource(corsConfigurationSource())
            }
            .authorizeHttpRequests {

                it.requestMatchers(HttpMethod.POST, "/auth/login").anonymous()
                it.requestMatchers(HttpMethod.POST, "/auth/signup").anonymous()

                it.requestMatchers("/error").permitAll()

                it.anyRequest().permitAll()

            }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(exceptionFilter, JwtFilter::class.java)
            .exceptionHandling {
                it.accessDeniedHandler(customAccessDeniedHandler)
                it.authenticationEntryPoint(customAuthenticationEntryPoint)
            }
            .build()

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        configuration.allowedMethods = listOf("*")
        configuration.allowedHeaders = listOf("*")
        configuration.maxAge = 3000L
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

}