package com.info.info_v2_backend.user.adapter.input.web.rest

import com.info.info_v2_backend.common.auth.Auth
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse
import com.info.info_v2_backend.common.user.StudentDto
import com.info.info_v2_backend.common.user.UserDto
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.response.CommonUserDetails
import com.info.info_v2_backend.common.user.ContactorDto
import com.info.info_v2_backend.user.application.port.input.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val loadCommonUserDetailsUsecase: LoadCommonUserDetailsUsecase,
    private val loadPasswordHintUsecase: LoadPasswordHintUsecase,
    private val loadContactorUsecase: LoadContactorUsecase,
    private val loadStudentUsecase: LoadStudentUsecase,
    private val loadTeacherUsecase: LoadTeacherUsecase,
    private val changePasswordUsecase: ChangePasswordUsecase,
    private val changeUserProfilePhotoUsecase: ChangeUserProfilePhotoUsecase
) {

    @PatchMapping("/photo")
    fun changePhoto(
        @RequestBody request: GenerateFileRequest
    ): PresignedUrlResponse {
        return changeUserProfilePhotoUsecase.upload(request, Auth.getUserEmail()?: throw BusinessException(null, ErrorCode.TOKEN_NEED_ERROR))
    }

    @GetMapping
    fun loadCommonUserDetails(@RequestParam userEmail: String): CommonUserDetails? {
        return loadCommonUserDetailsUsecase.load(userEmail)
    }

    @GetMapping("/exist")
    fun existsUserByEmail(@RequestParam userEmail: String): String {
        return loadCommonUserDetailsUsecase.load(userEmail)!!.username
    }

    @GetMapping("/password/hint")
    fun getPasswordHint(@RequestParam email: String): String? {
        return loadPasswordHintUsecase.load(email)
    }

    @GetMapping("/contactor")
    fun getContactor(@RequestParam companyNumber: String): ContactorDto {
        return loadContactorUsecase.loadContactor(companyNumber)
    }

    @GetMapping("/student")
    fun getStudent(@RequestParam studentEmail: String): StudentDto {
        return loadStudentUsecase.loadStudent(studentEmail)
    }

    @GetMapping("/info")
    fun getMyInformation(): UserDto {
        Auth.getUserEmail()?.let {
            if (Auth.checkIsTeacher()) {
                return loadTeacherUsecase.loadTeacher(it)
            } else {
                return loadStudentUsecase.loadStudent(it)
            }
        }?: throw BusinessException(errorCode = ErrorCode.TOKEN_NEED_ERROR)
    }

    @GetMapping("/grade/{grade}")
    fun getGenerationStudentList(
        @PathVariable grade: Int,
        @RequestParam classNum: Int?
    ): List<StudentDto> {
        if (Auth.checkIsTeacher()) return loadStudentUsecase.loadStudentListByGenerationAndClassNum(grade, classNum)
        else throw BusinessException(errorCode = ErrorCode.NO_AUTHORIZATION_ERROR)
    }

    //Internal
    @PutMapping("/password")
    fun changePassword(
        @RequestParam email: String,
        @RequestParam newPassword: String
    ) {
        if (Auth.checkIsSystem()) return changePasswordUsecase.change(email, newPassword)
        throw BusinessException(errorCode = ErrorCode.NO_AUTHORIZATION_ERROR)
    }

}
