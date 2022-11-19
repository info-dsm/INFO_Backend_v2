package com.info.info_v2_backend.applies.adapter.input.rest

import com.info.info_v2_backend.applies.adapter.input.rest.dto.respnose.AppliesResponse
import com.info.info_v2_backend.applies.application.port.input.*
import com.info.info_v2_backend.common.applies.AppliesDto
import com.info.info_v2_backend.common.applies.AppliesStatus
import com.info.info_v2_backend.common.auth.Auth
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RestController
class AppliesController(
    private val applyUsecase: ApplyAppliesUsecase,
    private val cancelApplyUsecase: CancelApplyUsecase,
    private val loadAppliesUsecase: LoadAppliesUsecase,
    private val approveAppliesUsecase: ApproveAppliesUsecase,
    private val rejectAppliesUsecase: RejectAppliesUsecase
) {

    @PostMapping("/{noticeId}")
    fun apply(
        @PathVariable noticeId: String,
        @RequestPart resume: MultipartFile
    ) {
        applyUsecase.apply(noticeId, resume, Auth.getUserEmail())
    }

    @DeleteMapping("/{noticeId}")
    fun cancelApply(
        @PathVariable noticeId: String
    ) {
        return cancelApplyUsecase.cancelApply(noticeId, Auth.getUserEmail())
    }

    @GetMapping("/{noticeId}")
    fun getAppliesList(@PathVariable noticeId: String, @RequestParam status: AppliesStatus): List<AppliesResponse> {
        if (!Auth.checkIsTeacher())
            throw BusinessException(
                "이 작업은 선생님만 수행할 수 있습니다.",
                ErrorCode.NO_AUTHORIZATION_ERROR
            )
        return loadAppliesUsecase.loadAppliesListByStatus(noticeId, status)
    }

    @PutMapping("/{noticeId}/approve/{studentEmail}")
    fun approveApplies(
        @PathVariable noticeId: String,
        @PathVariable studentEmail: String
    ) {
        if (!Auth.checkIsTeacher())
            throw BusinessException(
                "이 작업은 선생님만 수행할 수 있습니다.",
                ErrorCode.NO_AUTHORIZATION_ERROR
            )
        return approveAppliesUsecase.approve(noticeId, studentEmail)
    }


    @DeleteMapping("/{noticeId}/reject/{studentEmail}")
    fun rejectApplies(
        @PathVariable noticeId: String,
        @PathVariable studentEmail: String
    ) {
        if (!Auth.checkIsTeacher())
            throw BusinessException(
                "이 작업은 선생님만 수행할 수 있습니다.",
                ErrorCode.NO_AUTHORIZATION_ERROR
            )
        return rejectAppliesUsecase.reject(noticeId, studentEmail)
    }

    @GetMapping("/{noticeId}/{studentEmail}")
    fun getApplies(
        @PathVariable noticeId: String,
        @PathVariable studentEmail: String
    ): AppliesDto? {
        return loadAppliesUsecase.loadApplies(noticeId, studentEmail)
    }


}