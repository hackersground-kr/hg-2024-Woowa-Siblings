package kr.hs.dgsw.canbusserver.global.handler

import kr.hs.dgsw.canbusserver.global.exception.BusinessException
import kr.hs.dgsw.canbusserver.global.response.Response
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException::class)
    protected fun handleBusinessException(e: BusinessException): ResponseEntity<Response> {
        return responseEntityBuilder(
            httpStatus = HttpStatus.valueOf(e.errorCode.value),
            message = e.message
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleValidationException(e: MethodArgumentNotValidException): ResponseEntity<Response> {
        return responseEntityBuilder(
            httpStatus = HttpStatus.BAD_REQUEST,
            message = e.fieldErrors.joinToString(", ") { it.defaultMessage.toString() }
        )
    }

    @ExceptionHandler(InsufficientAuthenticationException::class)
    protected fun handleInsufficientAuthenticationException(e: InsufficientAuthenticationException): ResponseEntity<Response> {
        return responseEntityBuilder(
            httpStatus = HttpStatus.UNAUTHORIZED,
            message = "인증 정보가 없어요"
        )
    }

    @ExceptionHandler(AccessDeniedException::class)
    protected fun handleAccessDeniedException(e: AccessDeniedException): ResponseEntity<Response> {
        return responseEntityBuilder(
            httpStatus = HttpStatus.FORBIDDEN,
            message = "API에 접근하기 위한 권한이 없어요"
        )
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    protected fun handleMethodNotAllowedException(e: HttpRequestMethodNotSupportedException): ResponseEntity<Response> {
        return responseEntityBuilder(
            httpStatus = HttpStatus.METHOD_NOT_ALLOWED,
            message = "${e.method} 대신 ${e.supportedHttpMethods}를 써주세요"
        )
    }

    @ExceptionHandler(RuntimeException::class)
    protected fun handleRuntimeException(e: RuntimeException): ResponseEntity<Response> {
        e.printStackTrace()
        return responseEntityBuilder(
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
            message = e.toString()
        )
    }

    protected fun responseEntityBuilder(httpStatus: HttpStatus, message: String): ResponseEntity<Response> {
        return ResponseEntity
            .status(httpStatus)
            .body(
                Response(
                    status = httpStatus.value(),
                    code = httpStatus.reasonPhrase,
                    message = message
                )
            )
    }

    protected fun responseEntityBuilder(httpStatus: HttpStatus, mediaType: MediaType, message: String): ResponseEntity<Response> {
        return ResponseEntity
            .status(httpStatus)
            .contentType(mediaType)
            .body(
                Response(
                    status = httpStatus.value(),
                    code = httpStatus.reasonPhrase,
                    message = message
                )
            )
    }

}