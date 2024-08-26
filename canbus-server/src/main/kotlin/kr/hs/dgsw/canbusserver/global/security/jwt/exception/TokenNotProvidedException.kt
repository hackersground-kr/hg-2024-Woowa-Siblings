package kr.hs.dgsw.canbusserver.global.security.jwt.exception

import kr.hs.dgsw.canbusserver.global.exception.BusinessException
import kr.hs.dgsw.canbusserver.global.exception.ErrorCode

class TokenNotProvidedException : BusinessException(
    ErrorCode.UNAUTHORIZED,
    "토큰이 제공되지 않았어요"
)