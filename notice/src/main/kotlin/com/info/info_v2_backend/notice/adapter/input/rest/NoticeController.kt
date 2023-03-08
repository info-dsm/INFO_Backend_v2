package com.info.info_v2_backend.notice.adapter.input.rest

import com.info.info_v2_backend.common.auth.Auth
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse
import com.info.info_v2_backend.common.logs.LogFormat
import com.info.info_v2_backend.common.notice.NoticeDto
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.CreateNoticeRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.EditNoticeRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.classification.AddClassificationRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.*
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
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

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
    private val loadCertificateUsecase: LoadCertificateUsecase,
    private val countOpenNoticeUsecase: CountOpenNoticeUsecase
){
    private val log = LoggerFactory.getLogger(this.javaClass)


    //@Cacheable("memberCacheStore")
    @GetMapping("/count")
    fun getOpenNoticeCount(): Int {
        return countOpenNoticeUsecase.count()
    }

    //@Cacheable("memberCacheStore")
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

    //@Cacheable("memberCacheStore")
    @GetMapping("/technology")
    fun getTechnology(): List<TechnologyResponse> {
        return loadTechnologyUsecase.loadAll()
    }

    @PutMapping("/technology")
    @ResponseStatus(HttpStatus.CREATED)
    fun addTechnology(
        @RequestParam name: String
    ) {
        log.info("addTechnology: $name")
        if(Auth.checkIsTeacher()) return addTechnologyUsecase.add(name)
        else throw BusinessException(null, ErrorCode.NO_AUTHORIZATION_ERROR)
    }

    //@Cacheable("memberCacheStore")
    @GetMapping("/language")
    fun getLanguage(): List<LanguageResponse> {
        return loadLanguageUsecase.loadAll()
    }

    @PutMapping("/language")
    @ResponseStatus(HttpStatus.CREATED)
    fun addLanguage(
        @RequestParam name: String
    ) {
        log.info("addLanguage: $name")
        if(Auth.checkIsTeacher()) return addLanguageUsecase.add(name)
        else throw BusinessException(null, ErrorCode.NO_AUTHORIZATION_ERROR)
    }

    //@Cacheable("memberCacheStore")
    @GetMapping("/interview")
    fun getInterviewProcessList(): List<InterviewProcessResponse> {
        return loadInterviewProcessUsecase.loadAll().map {
            return@map InterviewProcessResponse(
                it.name,
                it.meaning
            )
        }
    }

    //@Cacheable("memberCacheStore")
    @GetMapping("/certificate")
    fun getCertificate(): List<CertificateResponse> {
        return loadCertificateUsecase.loadAll()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createNotice(
        @RequestBody request: CreateNoticeRequest,
        @RequestParam companyNumber: String,
    ): PresignedUrlListResponse {
        log.info("createNotice, companyNumber $companyNumber")
        if (Auth.checkIsTeacher()) return createNoticeUsecase.create(companyNumber, request)
        return createNoticeUsecase.create(Auth.checkCompanyNumber(companyNumber), request)
    }

    @CacheEvict("memberCacheStore", key = "#noticeId")
    @PatchMapping("/{companyNumber}/{noticeId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun editNotice(
        @PathVariable companyNumber: String,
        @PathVariable noticeId: String,
        @RequestBody request: EditNoticeRequest,
    ): PresignedUrlListResponse {
        log.info("editNotice, companyNumber: ${companyNumber}, noticeId: ${noticeId}")
        if (Auth.checkIsTeacher()) return editNoticeUsecase.edit(noticeId, request, companyNumber)
        else return editNoticeUsecase.edit(noticeId, request, Auth.checkCompanyNumber(companyNumber))
    }

    @PatchMapping("/{companyNumber}/{noticeId}/attachment")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun changeAttachment(
        @PathVariable companyNumber: String,
        @PathVariable noticeId: String,
        @RequestBody request: GenerateFileListRequest
    ): PresignedUrlListResponse {
        log.info("changeAttachment, companyNumber: ${companyNumber}, noticeId: ${noticeId}")
        if (Auth.checkIsTeacher()) return changeAttachmentUsecase.change(companyNumber, noticeId, request)
        else return changeAttachmentUsecase.change(Auth.checkCompanyNumber(companyNumber), noticeId, request)
    }

    @GetMapping("/waiting-list")
    fun getWaitingNoticeList(
        @RequestParam(defaultValue = "0") idx: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): Page<MinimumNoticeResponse> {
        if (Auth.checkIsTeacher()) return loadWaitingNoticeUsecase.load(idx, size)
        else throw BusinessException(errorCode = ErrorCode.NO_AUTHORIZATION_ERROR)
    }

    @PostMapping("/{companyNumber}/{noticeId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun approveNotice(
        @PathVariable companyNumber: String,
        @PathVariable noticeId: String
    ) {
        log.info("approveNotice, companyNumber: ${companyNumber}, noticeId: ${noticeId}")
        if (!Auth.checkIsTeacher())
            throw BusinessException("권한이 부족합니다.", ErrorCode.NO_AUTHORIZATION_ERROR)
        return approveNoticeUsecase.approve(companyNumber, noticeId)
    }

    @DeleteMapping("/{companyNumber}/{noticeId}/conclude")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun concludeNotice(
        @PathVariable companyNumber: String,
        @PathVariable noticeId: String
    ) {
        log.info("concludeNotice, companyNumber: ${companyNumber}, noticeId: ${noticeId}")
        if (!Auth.checkIsTeacher()) throw BusinessException("이 작업은 반드시 선생님만 진행할 수 있습니다.", ErrorCode.NO_AUTHORIZATION_ERROR)
        return concludeNoticeUsecase.conclude(noticeId)
    }

    @DeleteMapping("/{companyNumber}/{noticeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun removeNotice(
        @PathVariable companyNumber: String,
        @PathVariable noticeId: String
    ) {
        log.info("removeNotice, companyNumber: ${companyNumber}, noticeId: ${noticeId}")
        if (Auth.checkIsTeacher()) removeNoticeUsecase.remove(noticeId, companyNumber)
        else removeNoticeUsecase.remove(noticeId, Auth.checkCompanyNumber(companyNumber))
    }

    @Cacheable("memberCacheStore", key = "#noticeId")
    @GetMapping("/{noticeId}")
    fun getMaximumNotice(
        @PathVariable noticeId: String
    ): MaximumNoticeResponse {
        return loadNoticeUsecase.loadMaximumNotice(noticeId)
    }

    @GetMapping("/admin/{companyNumber}/{noticeId}")
    fun getAdminMaximumNotice(
        @PathVariable companyNumber: String,
        @PathVariable noticeId: String
    ): AdminMaximumNoticeResponse {
        if (Auth.checkIsTeacher()) { return loadNoticeUsecase.loadAdminMaximunNotice(noticeId)}
        else if (Auth.checkCompanyNumber(companyNumber).equals(companyNumber)) return loadNoticeUsecase.loadAdminMaximunNotice(noticeId)
        else throw BusinessException(errorCode = ErrorCode.NO_AUTHORIZATION_ERROR)
    }


    //@Cacheable("memberCacheStore")
    @GetMapping("/list/on")
    fun getNotEndedMinimumNoticeList(
        @RequestParam(defaultValue = "0") idx: Int,
        @RequestParam(defaultValue = "10") size: Int,
    ): Page<MinimumNoticeResponse> {
        return loadNoticeUsecase.loadNotEndedMinimumNoticeList(idx, size)
    }

    //@Cacheable("memberCacheStore")
    @GetMapping("/list/end")
    fun getEndedMinimumNoticeList(
        @RequestParam(defaultValue = "0") idx: Int,
        @RequestParam(defaultValue = "10") size: Int,
        @RequestParam status: NoticeWaitingStatus
    ): Page<MinimumNoticeResponse> {
        return loadNoticeUsecase.loadEndedMinimumNoticeList(idx, size)
    }

    //@Cacheable("memberCacheStore")
    @GetMapping("/list/{companyNumber}")
    fun getCompanyNoticeList(
        @PathVariable companyNumber: String
    ): List<MinimumNoticeResponse> {
        return loadNoticeUsecase.loadCompanyMinimumNoticeList(companyNumber)
    }

    @GetMapping("/list/every/{companyNumber}")
    fun getCompanyNoticeWithApproveStatusList(
        @PathVariable companyNumber: String
    ): List<MinimumNoticeWithApproveStatusResponse> {
        if (Auth.checkIsTeacher()) return loadNoticeUsecase.loadCompanyMinimumNoticeWithApproveStatusList(companyNumber)
        return loadNoticeUsecase.loadCompanyMinimumNoticeWithApproveStatusList(
            Auth.checkCompanyNumber(companyNumber)
        )
    }

    //internal
    @GetMapping("/available")
    fun loadAvailableNotice(@RequestParam noticeId: String): NoticeDto? {
        if (Auth.checkIsSystem()) return loadNoticeUsecase.loadAvailableNotice(noticeId)
        return null
    }

}