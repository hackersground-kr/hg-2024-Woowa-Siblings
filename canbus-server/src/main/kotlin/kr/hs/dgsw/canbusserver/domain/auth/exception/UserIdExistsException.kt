package kr.hs.dgsw.canbusserver.domain.auth.exception

import kr.hs.dgsw.canbusserver.global.exception.BusinessException
import kr.hs.dgsw.canbusserver.global.exception.ErrorCode

class UserIdExistsException : BusinessException(
    ErrorCode.BAD_REQUEST,
    "아이디가 이미 존재해요"
)
