package kr.hs.dgsw.canbusserver.global.response

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*

class DataResponse<T>(
    @JsonFormat(timezone = "Asia/Seoul")
    val timestamp: Date = Date(),
    val status: Int,
    val code: String,
    val message: String,
    val data: T
) {

    companion object {

        fun <T> ok(message: String, data: T): ResponseEntity<DataResponse<T>> {
            return of(HttpStatus.OK, message, data)
        }

        fun <T> created(message: String, data: T): ResponseEntity<DataResponse<T>> {
            return of(HttpStatus.CREATED, message, data)
        }

        private fun <T> of(status: HttpStatus, message: String, data: T): ResponseEntity<DataResponse<T>> {
            return ResponseEntity
                .status(status)
                .body(
                    DataResponse(
                        status = status.value(),
                        code = status.reasonPhrase,
                        message = message,
                        data = data
                    )
                )
        }

    }

}