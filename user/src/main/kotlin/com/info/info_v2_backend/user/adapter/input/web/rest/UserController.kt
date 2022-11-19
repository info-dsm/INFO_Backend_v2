package com.info.info_v2_backend.user.adapter.input.web.rest

import com.info.info_v2_backend.common.user.StudentDto
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveStudentDto
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.response.CommonUserDetails
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.response.ContactorResponse
import com.info.info_v2_backend.user.application.port.input.LoadCommonUserDetailsUsecase
import com.info.info_v2_backend.user.application.port.input.LoadContactorUsecase
import com.info.info_v2_backend.user.application.port.input.LoadPasswordHintUsecase
import com.info.info_v2_backend.user.application.port.input.LoadStudentUsecase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val loadCommonUserDetailsUsecase: LoadCommonUserDetailsUsecase,
    private val loadPasswordHintUsecase: LoadPasswordHintUsecase,
    private val loadContactorUsecase: LoadContactorUsecase,
    private val loadStudentUsecase: LoadStudentUsecase
) {

    @GetMapping
    fun loadCommonUserDetails(@RequestParam userEmail: String): CommonUserDetails? {
        return loadCommonUserDetailsUsecase.load(userEmail)
    }

    @GetMapping("/exist")
    fun existsUserByEmail(@RequestParam userEmail: String): String? {
        return loadCommonUserDetailsUsecase.load(userEmail)?.username
    }

    @GetMapping("/password/hint")
    fun getPasswordHint(@RequestParam email: String): String? {
        return loadPasswordHintUsecase.load(email)
    }

    @GetMapping("/contactor")
    fun getContactor(@RequestParam companyNumber: String): ContactorResponse {
        return loadContactorUsecase.loadContactor(companyNumber)
    }

    @GetMapping("/student")
    fun getStudent(@RequestParam studentEmail: String): StudentDto? {
        return loadStudentUsecase.loadStudent(studentEmail)
    }

}