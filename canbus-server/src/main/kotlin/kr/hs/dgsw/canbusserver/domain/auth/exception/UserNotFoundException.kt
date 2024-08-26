package kr.hs.dgsw.canbusserver.domain.auth.exception

import kr.hs.dgsw.canbusserver.global.exception.BusinessException
import kr.hs.dgsw.canbusserver.global.exception.ErrorCode

class UserNotFoundException : BusinessException(
    ErrorCode.NOT_FOUND,
    "유저를 찾을 수 없어요"
)