package kr.hs.dgsw.canbusserver.global.security

import kr.hs.dgsw.canbusserver.global.security.jwt.JwtUtil
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class SecurityUtil(
    private val jwtUtil: JwtUtil,
    private val customUserDetailService: CustomUserDetailService
) {

    fun getCurrentUserId(): Long =
        SecurityContextHolder.getContext().authentication.name.toLong()

    fun setAuthentication(accessToken: String) {

        val id = jwtUtil.getIdByAccessToken(accessToken)

        val authentication = customUserDetailService.loadUserByUsername(id.toString()).let {
            UsernamePasswordAuthenticationToken(it, it.password, it.authorities)
        }

        SecurityContextHolder.clearContext()
        SecurityContextHolder.getContext().authentication = authentication

    }

}