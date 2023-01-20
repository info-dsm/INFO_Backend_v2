package com.info.info_v2_backend.user.adapter.input.web.rest

import com.info.info_v2_backend.common.auth.Auth
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.user.StudentDto
import com.info.info_v2_backend.common.user.UserDto
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.response.CommonUserDetails
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.response.ContactorResponse
import com.info.info_v2_backend.user.application.port.input.LoadCommonUserDetailsUsecase
import com.info.info_v2_backend.user.application.port.input.LoadContactorUsecase
import com.info.info_v2_backend.user.application.port.input.LoadPasswordHintUsecase
import com.info.info_v2_backend.user.application.port.input.LoadStudentUsecase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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
    fun getStudent(@RequestParam studentEmail: String): StudentDto {
        return loadStudentUsecase.loadStudent(studentEmail)
    }

    @GetMapping("/info")
    fun getMyInformation(): UserDto {
        Auth.getUserEmail()?.let {
            try {
                return loadStudentUsecase.loadStudent(it)
            } catch (e: BusinessException) {
                return loadContactorUsecase.loadContactor(it)
            }
        }?: throw BusinessException(errorCode = ErrorCode.TOKEN_NEED_ERROR)
    }

    @GetMapping("/user/{generation}")
    fun getGenerationStudentList(
        @PathVariable generation: Int
    ): List<StudentDto> {
        return loadStudentUsecase.loadStudentListByGeneration(generation)
    }

}