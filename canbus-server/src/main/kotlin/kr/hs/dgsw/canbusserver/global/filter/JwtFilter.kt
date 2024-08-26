package kr.hs.dgsw.canbusserver.global.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.hs.dgsw.canbusserver.global.security.SecurityUtil
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter(
    private val securityUtil: SecurityUtil
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
    ) {

        if (request.getHeader("Authorization") != null) {
            val accessToken = request.getHeader("Authorization").removePrefix("Bearer ")
            securityUtil.setAuthentication(accessToken)
        }

        filterChain.doFilter(request, response)

    }


}