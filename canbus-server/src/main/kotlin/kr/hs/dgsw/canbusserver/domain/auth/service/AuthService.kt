package kr.hs.dgsw.canbusserver.domain.auth.service

import kr.hs.dgsw.canbusserver.domain.auth.exception.PasswordNotMatchedException
import kr.hs.dgsw.canbusserver.domain.auth.exception.UserIdExistsException
import kr.hs.dgsw.canbusserver.domain.auth.exception.UserNotFoundException
import kr.hs.dgsw.canbusserver.domain.auth.presentation.dto.request.LoginRequest
import kr.hs.dgsw.canbusserver.domain.auth.presentation.dto.request.SignupRequest
import kr.hs.dgsw.canbusserver.domain.auth.presentation.dto.response.TokenResponse
import kr.hs.dgsw.canbusserver.domain.user.User
import kr.hs.dgsw.canbusserver.domain.user.UserRepository
import kr.hs.dgsw.canbusserver.global.encrypt.EncryptUtil
import kr.hs.dgsw.canbusserver.global.security.jwt.JwtUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val encryptUtil: EncryptUtil,
    private val jwtUtil: JwtUtil,
) {

    @Transactional(readOnly = true)
    fun login(request: LoginRequest): TokenResponse {

        val user = userRepository.findByIdentifier(request.identifier) ?: throw UserNotFoundException()

        if (encryptUtil.match(request.password, user.password).not()) {
            throw PasswordNotMatchedException()
        }

        return TokenResponse(
            accessToken = jwtUtil.createAccessToken(user),
            refreshToken = jwtUtil.createRefreshToken(user)
        )
    }

    @Transactional
    fun signup(request: SignupRequest): TokenResponse {

        if (userRepository.existsByIdentifier(request.identifier)) {
            throw UserIdExistsException()
        }

        val encryptedPassword: String = encryptUtil.encode(request.password)

        val user = User(
            identifier = request.identifier,
            password = encryptedPassword,
        )

        userRepository.save(user)

        return TokenResponse(
            accessToken = jwtUtil.createAccessToken(user),
            refreshToken = jwtUtil.createRefreshToken(user)
        )
    }

}