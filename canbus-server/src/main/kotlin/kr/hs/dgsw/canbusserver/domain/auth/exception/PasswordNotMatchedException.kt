package kr.hs.dgsw.canbusserver.domain.auth.exception

import kr.hs.dgsw.canbusserver.global.exception.BusinessException
import kr.hs.dgsw.canbusserver.global.exception.ErrorCode

class PasswordNotMatchedException : BusinessException(
    ErrorCode.BAD_REQUEST,
    "비밀번호가 일치하지 않아요"
)