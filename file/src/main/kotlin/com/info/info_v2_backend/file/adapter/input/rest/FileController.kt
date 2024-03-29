package com.info.info_v2_backend.file.adapter.input.rest

import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest
import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.*
import com.info.info_v2_backend.file.application.port.input.announce.LoadAnnouncementFileUsecase
import com.info.info_v2_backend.file.application.port.input.announce.UploadAnnouncementFileUsecase
import com.info.info_v2_backend.file.application.port.input.applies.LoadResumeUsecase
import com.info.info_v2_backend.file.application.port.input.applies.RemoveResumeUsecase
import com.info.info_v2_backend.file.application.port.input.notice.UploadAttachmentUsecase
import com.info.info_v2_backend.file.application.port.input.company.LoadCompanyFileUsecase
import com.info.info_v2_backend.file.application.port.input.company.RemoveCompanyFileUsecase
import com.info.info_v2_backend.file.application.port.input.company.UploadCompanyFileUsecase
import com.info.info_v2_backend.file.application.port.input.applies.UploadResumeUsecase
import com.info.info_v2_backend.file.application.port.input.notice.LoadAttachmentUsecase
import com.info.info_v2_backend.file.application.port.input.notice.RemoveAttachmentUsecase
import com.info.info_v2_backend.file.application.port.input.user.LoadUserFileUsecase
import com.info.info_v2_backend.file.application.port.input.user.UploadUserFileUsecase
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class FileController(
    private val uploadCompanyFileUsecase: UploadCompanyFileUsecase,
    private val removeCompanyFileUsecase: RemoveCompanyFileUsecase,
    private val loadCompanyFileUsecase: LoadCompanyFileUsecase,
    private val uploadResumeUsecase: UploadResumeUsecase,
    private val uploadAttachmentUsecase: UploadAttachmentUsecase,
    private val loadAttachmentUsecase: LoadAttachmentUsecase,
    private val loadResumeUsecase: LoadResumeUsecase,
    private val removeResumeUsecase: RemoveResumeUsecase,
    private val removeAttachmentUsecase: RemoveAttachmentUsecase,
    private val uploadAnnouncementFileUsecase: UploadAnnouncementFileUsecase,
    private val loadAnnouncementFileUsecase: LoadAnnouncementFileUsecase,
    private val uploadUserFileUsecase: UploadUserFileUsecase,
    private val loadUserFileUsecase: LoadUserFileUsecase
) {
    private val log = LoggerFactory.getLogger(this.javaClass)


    @PutMapping("/company")
    fun uploadCompanyFile(
        @RequestParam companyNumber: String,
        @RequestParam classification: CompanyFileClassificationType,
        @RequestBody request: GenerateFileRequest
    ): PresignedUrlResponse {
        val response = uploadCompanyFileUsecase.uploadCompanyFile(
            request, classification, companyNumber
        )
        log.info("companyNumber: $companyNumber, classification: $classification, fileName: ${request.fileName}, fileUrl: ${response.url}")
        return response
    }


    @GetMapping("/company/{companyNumber}")
    fun getCompanyPhotoFileList(@PathVariable companyNumber: String): List<CompanyFileResponse> {
        return loadCompanyFileUsecase.loadPhotosByCompanyNumber(companyNumber)
    }


    @DeleteMapping("/company/{companyNumber}/{fileId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun removeCompanyFile(
        @PathVariable companyNumber: String,
        @PathVariable fileId: String,
        @RequestParam classificationType: CompanyFileClassificationType
    ) {
        removeCompanyFileUsecase.remove(companyNumber, fileId, classificationType)
    }


    @PutMapping("/applies/{noticeId}/{studentEmail}/resume")
    fun uploadResume(
        @PathVariable noticeId: String,
        @PathVariable studentEmail: String,
        @RequestBody request: GenerateFileListRequest
    ): PresignedUrlListResponse {
        removeResumeUsecase.remove(noticeId, studentEmail)
        return PresignedUrlListResponse(
            request.request.map {
                    geneFileRequest: GenerateFileRequest ->
                return@map PresignedUrlResponse(
                    uploadResumeUsecase.uploadResume(
                        geneFileRequest,
                        noticeId,
                        studentEmail
                    ).url,
                    geneFileRequest.fileName
                )
            }.toMutableList()
        )
    }


    @GetMapping("/applies/{noticeId}/{studentEmail}/resume")
    fun getAppliesResume(
        @PathVariable noticeId: String,
        @PathVariable studentEmail: String
    ): List<FileResponse> {
        return loadResumeUsecase.load(noticeId, studentEmail)
    }


    @DeleteMapping("/applies/{noticeId}/{studentEmail}/resume")
    fun removeResume(
        @PathVariable noticeId: String,
        @PathVariable studentEmail: String
    ) {
        return removeResumeUsecase.remove(noticeId, studentEmail)
    }



    @PutMapping("/notice/{noticeId}/attachment")
    fun uploadAttachment(
        @PathVariable noticeId: String,
        @RequestBody request: GenerateFileListRequest
    ): PresignedUrlListResponse {
        removeAttachmentUsecase.remove(noticeId)
        return PresignedUrlListResponse(
            request.request.map {
                geneFileRequest: GenerateFileRequest ->
                return@map PresignedUrlResponse(
                        uploadAttachmentUsecase.uploadAttachment(
                            geneFileRequest,
                            noticeId
                        ).url,
                    geneFileRequest.fileName
                )
            }.toMutableList()
        )
    }

    @GetMapping("/notice/{noticeId}/attachment")
    fun getAttachmentList(
        @PathVariable noticeId: String
    ): List<AttachmentResponse> {
        return loadAttachmentUsecase.loadAttachmentList(noticeId)
    }

    @DeleteMapping("/notice/{noticeId}/attachment")
    fun removeAttachment(
        @PathVariable noticeId: String
    ) {
        return removeAttachmentUsecase.remove(noticeId)
    }


    @PostMapping("/announcement/{announcementId}")
    fun uploadAnnouncementFileList(
        @PathVariable announcementId: Long,
        @RequestBody request: GenerateFileListRequest
    ): PresignedUrlListResponse {
        return PresignedUrlListResponse(
            request.request.map {
                    geneFileRequest: GenerateFileRequest ->
                return@map PresignedUrlResponse(
                    uploadAnnouncementFileUsecase.upload(
                        announcementId,
                        geneFileRequest
                    ).url,
                    geneFileRequest.fileName
                )
            }.toMutableList()
        )
    }

    @GetMapping("/announcement/{announcementId}")
    fun loadAnnouncementFileList(
        @PathVariable announcementId: Long
    ): List<AnnouncementFileResponse> {
        return loadAnnouncementFileUsecase.load(announcementId)
    }




    @PostMapping("/user/{userEmail}")
    fun uploadUserProfilePhoto(
        @PathVariable userEmail: String,
        @RequestBody request: GenerateFileRequest
    ): PresignedUrlResponse {
        return uploadUserFileUsecase.uploadUserProfilePhoto(
            request,
            userEmail
        )
    }

    @GetMapping("/user/{userEmail}")
    fun loadUserProfilePhoto(
        @PathVariable userEmail: String
    ): UserFileResponse? {
        return loadUserFileUsecase.load(userEmail)
    }


}
