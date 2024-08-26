package kr.hs.dgsw.canbusserver.global.exception

open class BusinessException(
    val errorCode: ErrorCode,
    override val message: String
) : RuntimeException(message)