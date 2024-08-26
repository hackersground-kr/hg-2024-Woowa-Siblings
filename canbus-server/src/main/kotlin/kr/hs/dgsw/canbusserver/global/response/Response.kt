package kr.hs.dgsw.canbusserver.global.response

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*

class Response(
    @JsonFormat(timezone = "Asia/Seoul")
    val timestamp: Date = Date(),
    val status: Int,
    val code: String,
    val message: String
) {

    companion object {

        fun ok(message: String): ResponseEntity<Response> {
            return of(HttpStatus.OK, message)
        }

        fun created(message: String): ResponseEntity<Response> {
            return of(HttpStatus.CREATED, message)
        }

        private fun of(status: HttpStatus, message: String): ResponseEntity<Response> {
            return ResponseEntity
                .status(status)
                .body(
                    Response(
                        status = status.value(),
                        code = status.reasonPhrase,
                        message = message
                    )
                )
        }

    }

}