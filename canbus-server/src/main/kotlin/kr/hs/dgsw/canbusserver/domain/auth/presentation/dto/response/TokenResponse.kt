package kr.hs.dgsw.canbusserver.domain.auth.presentation.dto.response

class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)