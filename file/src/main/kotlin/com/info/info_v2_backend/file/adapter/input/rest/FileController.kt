package com.info.info_v2_backend.file.adapter.input.rest

import com.info.info_v2_backend.common.file.dto.AttachmentResponse
import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.dto.response.CompanyFileResponse
import com.info.info_v2_backend.common.file.dto.response.FileResponse
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse
import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest
import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.file.application.port.input.applies.LoadResumeUsecase
import com.info.info_v2_backend.file.application.port.input.notice.UploadAttachmentUsecase
import com.info.info_v2_backend.file.application.port.input.company.LoadCompanyFileUsecase
import com.info.info_v2_backend.file.application.port.input.company.RemoveCompanyFileUsecase
import com.info.info_v2_backend.file.application.port.input.company.UploadCompanyFileUsecase
import com.info.info_v2_backend.file.application.port.input.applies.UploadResumeUsecase
import com.info.info_v2_backend.file.application.port.input.notice.LoadAttachmentUsecase
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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
    private val loadResumeUsecase: LoadResumeUsecase
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
    fun getCompanyFileList(@PathVariable companyNumber: String): List<CompanyFileResponse> {
        return loadCompanyFileUsecase.loadByCompanyNumber(companyNumber)
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




    @PutMapping("/notice/{noticeId}/attachment")
    fun uploadAttachment(
        @PathVariable noticeId: String,
        @RequestBody request: GenerateFileListRequest
    ): PresignedUrlListResponse {
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


}