package com.info.info_v2_backend.file.adapter.input.rest

import com.info.info_v2_backend.common.file.FileConvert
import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.dto.response.CompanyFileResponse
import com.info.info_v2_backend.file.application.port.input.LoadCompanyFileUsecase
import com.info.info_v2_backend.file.application.port.input.RemoveCompanyFileUsecase
import com.info.info_v2_backend.file.application.port.input.UploadCompanyFileUsecase
import com.info.info_v2_backend.file.application.port.input.UploadResumeUsecase
import com.info.info_v2_backend.file.application.port.output.LoadCompanyFilePort
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
    private val uploadResumeUsecase: UploadResumeUsecase
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


    @PutMapping("/notice/{noticeId}/resume")
    fun uploadResume(
        @PathVariable noticeId: String,
        @RequestPart(value = "resume") resume: MultipartFile
    ) {
        uploadResumeUsecase.uploadResume(
            FileConvert.fileToMultipartFileConvert(
                FileConvert.multipartFileToFileConvert(resume,
                "$IMAGE_PATH${resume.originalFilename}"
                )
            ),
            noticeId
        )
        FileConvert.removeLocalFile("$IMAGE_PATH${resume.originalFilename}")
    }


}