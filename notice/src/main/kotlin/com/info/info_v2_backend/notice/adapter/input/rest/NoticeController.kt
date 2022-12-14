package com.info.info_v2_backend.notice.adapter.input.rest

import com.info.info_v2_backend.common.auth.Auth
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.notice.NoticeDto
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.CreateNoticeRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.EditNoticeRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.classification.AddClassificationRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.LanguageResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MaximumNoticeResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MinimumNoticeResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MinimumNoticeWithApproveStatusResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.certificate.CertificateResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.classification.ClassificationResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.interview.InterviewProcessResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.technology.TechnologyResponse
import com.info.info_v2_backend.notice.application.port.input.*
import com.info.info_v2_backend.notice.application.port.input.certificate.LoadCertificateUsecase
import com.info.info_v2_backend.notice.application.port.input.change.*
import com.info.info_v2_backend.notice.application.port.input.classification.AddClassificationUsecase
import com.info.info_v2_backend.notice.application.port.input.classification.LoadClassificationUsecase
import com.info.info_v2_backend.notice.application.port.input.interview.LoadInterviewProcessUsecase
import com.info.info_v2_backend.notice.application.port.input.language.AddLanguageUsecase
import com.info.info_v2_backend.notice.application.port.input.language.LoadLanguageUsecase
import com.info.info_v2_backend.notice.application.port.input.technology.AddTechnologyUsecase
import com.info.info_v2_backend.notice.application.port.input.technology.LoadTechnologyUsecase
import com.info.info_v2_backend.notice.domain.status.NoticeWaitingStatus
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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
    private val loadClassificationUsecase: LoadClassificationUsecase,
    private val addClassificationUsecase: AddClassificationUsecase,
    private val loadTechnologyUsecase: LoadTechnologyUsecase,
    private val addTechnologyUsecase: AddTechnologyUsecase,
    private val loadLanguageUsecase: LoadLanguageUsecase,
    private val addLanguageUsecase: AddLanguageUsecase,
    private val loadInterviewProcessUsecase: LoadInterviewProcessUsecase,
    private val loadCertificateUsecase: LoadCertificateUsecase
){


    @GetMapping("/classification")
    fun getClassification(): List<ClassificationResponse> {
        return loadClassificationUsecase.load()
    }

    @PutMapping("/classification")
    @ResponseStatus(HttpStatus.CREATED)
    fun addClassification(
        @RequestBody request: AddClassificationRequest
    ) {
        if(Auth.checkIsTeacher()) return addClassificationUsecase.addClassification(request)
        else throw BusinessException(null, ErrorCode.NO_AUTHORIZATION_ERROR)
    }

    @GetMapping("/technology")
    fun getTechnology(): List<TechnologyResponse> {
        return loadTechnologyUsecase.loadAll()
    }

    @PutMapping("/technology")
    @ResponseStatus(HttpStatus.CREATED)
    fun addTechnology(
        @RequestParam name: String
    ) {
        if(Auth.checkIsTeacher()) return addTechnologyUsecase.add(name)
        else throw BusinessException(null, ErrorCode.NO_AUTHORIZATION_ERROR)
    }

    @GetMapping("/language")
    fun getLanguage(): List<LanguageResponse> {
        return loadLanguageUsecase.loadAll()
    }

    @PutMapping("/language")
    @ResponseStatus(HttpStatus.CREATED)
    fun addLanguage(
        @RequestParam name: String
    ) {
        if(Auth.checkIsTeacher()) return addLanguageUsecase.add(name)
        else throw BusinessException(null, ErrorCode.NO_AUTHORIZATION_ERROR)
    }

    @GetMapping("/interview")
    fun getInterviewProcessList(): List<InterviewProcessResponse> {
        return loadInterviewProcessUsecase.loadAll().map {
            return@map InterviewProcessResponse(
                it.name,
                it.meaning
            )
        }
    }

    @GetMapping("/certificate")
    fun getCertificate(): List<CertificateResponse> {
        return loadCertificateUsecase.loadAll()
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createNotice(
        @RequestPart request: CreateNoticeRequest,
        @RequestParam companyNumber: String,
        @RequestPart attachment: List<MultipartFile>
    ){
        if (Auth.checkIsTeacher()) return createNoticeUsecase.create(Auth.checkCompanyNumber(companyNumber), request, attachment.takeIf {
            it.isNotEmpty()
        }?: throw BusinessException(null, ErrorCode.INPUT_DATA_NOT_FOUND_ERROR))
        return createNoticeUsecase.create(Auth.checkCompanyNumber(companyNumber), request, attachment.takeIf {
            it.isNotEmpty()
        }?: throw BusinessException(null, ErrorCode.INPUT_DATA_NOT_FOUND_ERROR))
    }

    @PatchMapping("/{companyNumber}/{noticeId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun editNotice(
        @PathVariable companyNumber: String,
        @PathVariable noticeId: String,
        @RequestBody request: EditNoticeRequest,
    ) {
        if (Auth.checkIsTeacher()) editNoticeUsecase.edit(noticeId, request, companyNumber)
        else editNoticeUsecase.edit(noticeId, request, Auth.checkCompanyNumber(companyNumber))
    }

    @PatchMapping("/{companyNumber}/{noticeId}/attachment")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun changeAttachment(
        @PathVariable companyNumber: String,
        @PathVariable noticeId: String,
        @RequestPart file: List<MultipartFile>
    ) {
        if (Auth.checkIsTeacher()) changeAttachmentUsecase.change(companyNumber, noticeId, file)
        else changeAttachmentUsecase.change(Auth.checkCompanyNumber(companyNumber), noticeId, file)
    }

    @GetMapping("/waiting-list")
    fun getWaitingNoticeList(
        @RequestParam(defaultValue = "0") idx: Int,
        @RequestParam(defaultValue = "10") size: Int
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
            throw BusinessException("????????? ???????????????.", ErrorCode.NO_AUTHORIZATION_ERROR)
        return approveNoticeUsecase.approve(companyNumber, noticeId)
    }

    @DeleteMapping("/{companyNumber}/{noticeId}/conclude")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun concludeNotice(@PathVariable companyNumber: String, @PathVariable noticeId: String) {
        if (!Auth.checkIsTeacher()) throw BusinessException("??? ????????? ????????? ???????????? ????????? ??? ????????????.", ErrorCode.NO_AUTHORIZATION_ERROR)
        return concludeNoticeUsecase.conclude(noticeId)
    }

    @DeleteMapping("/{companyNumber}/{noticeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun removeNotice(@PathVariable companyNumber: String, @PathVariable noticeId: String) {
        if (Auth.checkIsTeacher()) removeNoticeUsecase.remove(noticeId, companyNumber)
        else removeNoticeUsecase.remove(noticeId, Auth.checkCompanyNumber(companyNumber))
    }

    @GetMapping("/{noticeId}")
    fun getMaximumNotice(@PathVariable noticeId: String): MaximumNoticeResponse {
        return loadNoticeUsecase.loadMaximumNotice(noticeId)
    }


    @GetMapping("/list/on")
    fun getNotEndedMinimumNoticeList(
        @RequestParam(defaultValue = "0") idx: Int,
        @RequestParam(defaultValue = "10") size: Int,
    ): Page<MinimumNoticeResponse> {
        return loadNoticeUsecase.loadNotEndedMinimumNoticeList(idx, size)
    }

    @GetMapping("/list/end")
    fun getEndedMinimumNoticeList(
        @RequestParam(defaultValue = "0") idx: Int,
        @RequestParam(defaultValue = "10") size: Int,
        @RequestParam status: NoticeWaitingStatus
    ): Page<MinimumNoticeResponse> {
        return loadNoticeUsecase.loadEndedMinimumNoticeList(idx, size)
    }

    @GetMapping("/list/{companyNumber}")
    fun getCompanyNoticeList(
        @PathVariable companyNumber: String
    ): List<MinimumNoticeResponse> {
        return loadNoticeUsecase.loadCompanyMiniumumNoticeList(companyNumber)
    }

    @GetMapping("/list/every/{companyNumber}")
    fun getCompanyNoticeWithApproveStatusList(
        @PathVariable companyNumber: String
    ): List<MinimumNoticeWithApproveStatusResponse> {
        if (Auth.checkIsTeacher()) return loadNoticeUsecase.loadCompanyMiniumumNoticeWithApproveStatusList(companyNumber)
        return loadNoticeUsecase.loadCompanyMiniumumNoticeWithApproveStatusList(
            Auth.checkCompanyNumber(companyNumber)
        )
    }


    @GetMapping("/available")
    fun loadAvailableNotice(@RequestParam noticeId: String): NoticeDto? {
        return loadNoticeUsecase.loadAvailableNotice(noticeId)
    }

}