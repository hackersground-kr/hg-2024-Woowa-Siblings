package kr.hs.dgsw.canbusserver.domain.auth.exception

import kr.hs.dgsw.canbusserver.global.exception.BusinessException
import kr.hs.dgsw.canbusserver.global.exception.ErrorCode

class EmailExistsException : BusinessException(
    ErrorCode.BAD_REQUEST,
    "이메일이 이미 존재해요"
)
