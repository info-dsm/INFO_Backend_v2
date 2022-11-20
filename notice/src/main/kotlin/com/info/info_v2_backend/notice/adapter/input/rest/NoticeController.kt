package com.info.info_v2_backend.notice.adapter.input.rest

import com.info.info_v2_backend.common.auth.Auth
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.notice.NoticeDto
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.CreateNoticeRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.EditNoticeRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MaximumNoticeResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MinimumNoticeResponse
import com.info.info_v2_backend.notice.application.port.input.*
import com.info.info_v2_backend.notice.application.port.input.change.ChangeAttachmentUsecase
import com.info.info_v2_backend.notice.application.port.input.change.ChangeCertificateUsecase
import com.info.info_v2_backend.notice.application.port.input.change.ChangeInterviewProcessUsecase
import com.info.info_v2_backend.notice.application.port.input.change.ChangeTechnologyUsecase
import com.info.info_v2_backend.notice.domain.interview.InterviewProcess
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class NoticeController(
    private val createNoticeUsecase: CreateNoticeUsecase,
    private val editNoticeUsecase: EditNoticeUsecase,
    private val loadNoticeUsecase: LoadNoticeUsecase,
    private val removeNoticeUsecase: RemoveNoticeUsecase,
    private val concludeNoticeUsecase: ConcludeNoticeUsecase,
    private val approveNoticeUsecase: ApproveNoticeUsecase,
    private val loadWaitingNoticeUsecase: LoadWaitingNoticeUsecase,
    private val changeAttachmentUsecase: ChangeAttachmentUsecase,
    private val changeInterviewProcessUsecase: ChangeInterviewProcessUsecase,
    private val changeCertificateUsage: ChangeCertificateUsecase,
    private val changeTechnologyUsecase: ChangeTechnologyUsecase
){

    @PostMapping
    fun createNotice(
        @RequestBody request: CreateNoticeRequest,
        @RequestParam companyNumber: String,
        @RequestPart attachment: List<MultipartFile>
    ){
        return createNoticeUsecase.create(Auth.checkCompanyNumber(companyNumber), request, attachment)
    }

    @PatchMapping("/{companyNumber}/{noticeId}")
    fun editNotice(
        @PathVariable companyNumber: String,
        @PathVariable noticeId: String,
        @RequestBody request: EditNoticeRequest,
    ) {
        return editNoticeUsecase.edit(noticeId, request, Auth.checkCompanyNumber(companyNumber))
    }

    @PatchMapping("/{companyNumber}/{noticeId}/attachment")
    fun changeAttachment(
        @PathVariable companyNumber: String,
        @PathVariable noticeId: String,
        @RequestPart file: List<MultipartFile>
    ) {
        return changeAttachmentUsecase.change(Auth.checkCompanyNumber(companyNumber), noticeId, file)
    }

    @PatchMapping("/{companyNumber}/{noticeId}/interview")
    fun changeInterview(
        @PathVariable companyNumber: String,
        @PathVariable noticeId: String,
        @RequestBody interviewProcessMap: Map<Int, InterviewProcess>
    ) {
        Auth.checkCompanyNumber(companyNumber)
        changeInterviewProcessUsecase.change(noticeId, interviewProcessMap)
    }

    @PatchMapping("/{companyNumber}/{noticeId}/certificate")
    fun changeCertificate(
        @PathVariable companyNumber: String,
        @PathVariable noticeId: String,
        @RequestBody certificateList: List<String>
    ) {
        Auth.checkCompanyNumber(companyNumber)
        changeCertificateUsage.change(noticeId, certificateList)
    }

    @PatchMapping("/{companyNumber}/{noticeId}/technology")
    fun changeTechnology(
        @PathVariable companyNumber: String,
        @PathVariable noticeId: String,
        @RequestBody technologyList: MutableList<String>
    ) {
        Auth.checkCompanyNumber(companyNumber)
        changeTechnologyUsecase.change(noticeId, technologyList)
    }


    @GetMapping("/waiting-list")
    fun getWaitingNoticeList(
        @RequestParam idx: Int,
        @RequestParam size: Int
    ): Page<MinimumNoticeResponse> {
        return loadWaitingNoticeUsecase.load(idx, size)
    }

    @PostMapping("/{companyNumber}/{noticeId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun approveNotice(
        @PathVariable companyNumber: String,
        @PathVariable noticeId: String
    ) {
        if (!Auth.checkIsTeacher())
            throw BusinessException("권한이 부족합니다.", ErrorCode.NO_AUTHORIZATION_ERROR)
        return approveNoticeUsecase.approve(companyNumber, noticeId)
    }

    @DeleteMapping("/{companyNumber}/{noticeId}/conclude")
    fun concludeNotice(@PathVariable companyNumber: String, @PathVariable noticeId: String) {
        if (!Auth.checkIsTeacher()) throw BusinessException("이 작업은 반드시 선생님만 진행할 수 있습니다.", ErrorCode.NO_AUTHORIZATION_ERROR)
        return concludeNoticeUsecase.conclude(noticeId)
    }

    @DeleteMapping("/{companyNumber}/{noticeId}")
    fun removeNotice(@PathVariable companyNumber: String, @PathVariable noticeId: String) {
        return removeNoticeUsecase.remove(noticeId, companyNumber)
    }

    @GetMapping("/{noticeId}")
    fun getMaximumNotice(@PathVariable noticeId: String): MaximumNoticeResponse {
        return loadNoticeUsecase.loadMaximumNotice(noticeId)
    }


    @GetMapping("/list")
    fun getMinimumNoticeList(@RequestParam idx: Int, @RequestParam size: Int): Page<MinimumNoticeResponse> {
        return loadNoticeUsecase.loadMinimumNoticeList(idx, size)
    }

    @GetMapping("/available")
    fun loadAvailableNotice(@RequestParam noticeId: String): NoticeDto? {
        return loadNoticeUsecase.loadAvailableNotice(noticeId)
    }

}