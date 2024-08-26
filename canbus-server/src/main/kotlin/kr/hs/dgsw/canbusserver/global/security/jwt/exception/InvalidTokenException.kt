package kr.hs.dgsw.canbusserver.global.security.jwt.exception

import kr.hs.dgsw.canbusserver.global.exception.BusinessException
import kr.hs.dgsw.canbusserver.global.exception.ErrorCode

class InvalidTokenException : BusinessException(
    ErrorCode.UNAUTHORIZED,
    "유효하지 않은 토큰이에요"
)