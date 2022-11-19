package com.info.info_v2_backend.email.adapter.input.rest

import com.info.info_v2_backend.common.email.dto.SendEmailTextRequest
import com.info.info_v2_backend.email.adapter.output.rest.UserFeignClient
import com.info.info_v2_backend.email.application.port.input.SendEmailUsecase
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class EmailController(
    private val sendEmailUsecase: SendEmailUsecase,
    private val userFeignClient: UserFeignClient
) {

    @PutMapping("/send")
    fun sendEmailText(
        @RequestBody request: SendEmailTextRequest
    ) {
        sendEmailUsecase.sendEmailTextCommand(request, null)
    }

}