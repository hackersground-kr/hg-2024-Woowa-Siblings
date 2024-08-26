package kr.hs.dgsw.canbusserver.domain.auth.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import kr.hs.dgsw.canbusserver.domain.auth.presentation.dto.request.LoginRequest
import kr.hs.dgsw.canbusserver.domain.auth.presentation.dto.request.SignupRequest
import kr.hs.dgsw.canbusserver.domain.auth.presentation.dto.response.TokenResponse
import kr.hs.dgsw.canbusserver.domain.auth.service.AuthService
import kr.hs.dgsw.canbusserver.global.response.DataResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/auth")
@Tag(name = "AuthController", description = "Auth API 문서")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/login")
    @Operation(summary = "로그인 API")
    fun login(@RequestBody @Valid request: LoginRequest): ResponseEntity<DataResponse<TokenResponse>> {
        val response = authService.login(request)
        return DataResponse.ok("로그인에 성공했어요", response)
    }

    @PostMapping("/signup")
    @Operation(summary = "회원가입 API")
    fun signup(@RequestBody @Valid request: SignupRequest): ResponseEntity<DataResponse<TokenResponse>> {
        val response = authService.signup(request)
        return DataResponse.ok("회원가입에 성공했어요", response)
    }

}