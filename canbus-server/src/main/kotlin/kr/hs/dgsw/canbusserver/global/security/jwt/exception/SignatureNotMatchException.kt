package kr.hs.dgsw.canbusserver.global.security.jwt.exception

import kr.hs.dgsw.canbusserver.global.exception.BusinessException
import kr.hs.dgsw.canbusserver.global.exception.ErrorCode

class SignatureNotMatchException : BusinessException(
    ErrorCode.UNAUTHORIZED,
    "서명이 일치하지 않아요"
)