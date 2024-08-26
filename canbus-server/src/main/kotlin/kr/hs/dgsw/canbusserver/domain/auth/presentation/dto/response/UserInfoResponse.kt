package kr.hs.dgsw.canbusserver.domain.auth.presentation.dto.response

class UserInfoResponse(
    val id: Long,
    val identifier: String,
    val email: String,
    val nickname: String,
    val profileImage: String?,
)