package kr.hs.dgsw.canbusserver.global.security

import kr.hs.dgsw.canbusserver.domain.auth.exception.UserNotFoundException
import kr.hs.dgsw.canbusserver.domain.user.User
import kr.hs.dgsw.canbusserver.domain.user.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailService(
    private val userRepository: UserRepository,
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findByIdOrNull(username.toLong()) ?.let { toUserDetails(it) } ?: throw UserNotFoundException()

    private fun toUserDetails(user: User): UserDetails =
        CustomUser(user)

}