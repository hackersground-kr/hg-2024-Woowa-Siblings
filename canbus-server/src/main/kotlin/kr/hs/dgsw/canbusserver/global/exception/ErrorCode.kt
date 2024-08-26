package kr.hs.dgsw.canbusserver.global.exception

enum class ErrorCode(
    val value: Int,
) {

    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404)

}