package kr.hs.dgsw.canbusserver.global.security.jwt.exception

import kr.hs.dgsw.canbusserver.global.exception.BusinessException
import kr.hs.dgsw.canbusserver.global.exception.ErrorCode

class ExpiredTokenException : BusinessException(
    ErrorCode.UNAUTHORIZED,
    "토큰이 만료되었어요"
)