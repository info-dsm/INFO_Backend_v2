package com.info.info_v2_backend.file.adapter.input.rest

import com.info.info_v2_backend.common.file.FileConvert
import com.info.info_v2_backend.common.file.dto.AttachmentResponse
import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.dto.response.CompanyFileResponse
import com.info.info_v2_backend.file.application.port.input.notice.UploadAttachmentUsecase
import com.info.info_v2_backend.file.application.port.input.company.LoadCompanyFileUsecase
import com.info.info_v2_backend.file.application.port.input.company.RemoveCompanyFileUsecase
import com.info.info_v2_backend.file.application.port.input.company.UploadCompanyFileUsecase
import com.info.info_v2_backend.file.application.port.input.applies.UploadResumeUsecase
import com.info.info_v2_backend.file.application.port.input.notice.LoadAttachmentUsecase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class FileController(
    private val uploadCompanyFileUsecase: UploadCompanyFileUsecase,
    private val removeCompanyFileUsecase: RemoveCompanyFileUsecase,
    private val loadCompanyFileUsecase: LoadCompanyFileUsecase,
    private val uploadResumeUsecase: UploadResumeUsecase,
    private val uploadAttachmentUsecase: UploadAttachmentUsecase,
    private val loadAttachmentUsecase: LoadAttachmentUsecase
) {
    companion object {
        const val IMAGE_PATH = "file/src/main/resources/tmp/"
    }

    @PutMapping("/company")
    fun uploadCompanyFile(
        @RequestParam companyNumber: String,
        @RequestParam classification: CompanyFileClassificationType,
        @RequestPart(value = "file") file: MultipartFile
    ) {
        uploadCompanyFileUsecase.uploadCompanyFile(
            FileConvert.fileToMultipartFileConvert(
                FileConvert.multipartFileToFileConvert(file, "file/src/main/resources/tmp/")
            ), classification, companyNumber)
        FileConvert.removeLocalFile("$IMAGE_PATH${file.originalFilename}")
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
        @RequestPart(value = "resume") resume: MultipartFile
    ) {
        uploadResumeUsecase.uploadResume(
            FileConvert.fileToMultipartFileConvert(
                FileConvert.multipartFileToFileConvert(resume,
                "$IMAGE_PATH${resume.originalFilename}"
                )
            ),
            noticeId,
            studentEmail
        )
        FileConvert.removeLocalFile("$IMAGE_PATH${resume.originalFilename}")
    }

    @PutMapping("/notice/{noticeId}/attachment")
    fun uploadAttachment(
        @PathVariable noticeId: String,
        @RequestPart attachment: List<MultipartFile>
    ) {
        attachment.map {
            uploadAttachmentUsecase.uploadAttachment(
                FileConvert.fileToMultipartFileConvert(
                    FileConvert.multipartFileToFileConvert(it,
                        "$IMAGE_PATH${it.originalFilename}"
                    )
                ),
                noticeId
            )
            FileConvert.removeLocalFile("$IMAGE_PATH${it.originalFilename}")
        }
    }

    @GetMapping("/notice/{noticeId}/attachment")
    fun getAttachmentList(
        @PathVariable noticeId: String
    ): List<AttachmentResponse> {
        return loadAttachmentUsecase.loadAttachmentList(noticeId)
    }


}