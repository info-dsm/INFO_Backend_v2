package com.info.info_v2_backend.file.application.service

import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.dto.RegisterCompanyFileDto
import com.info.info_v2_backend.file.application.port.input.UploadCompanyFileUsecase
import com.info.info_v2_backend.file.application.port.input.UploadResumeUsecase
import com.info.info_v2_backend.file.application.port.output.RegisterCompanyFilePort
import com.info.info_v2_backend.file.application.port.output.RemoveFilePort
import com.info.info_v2_backend.file.application.port.output.UploadFilePort
import com.info.info_v2_backend.file.application.port.output.save.SaveCompanyFilePort
import com.info.info_v2_backend.file.application.port.output.save.SaveResumeFilePort
import com.info.info_v2_backend.file.domain.applies.Resume
import com.info.info_v2_backend.file.domain.company.CompanyFile
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*


@Service
class UploadFile(
    private val uploadFilePort: UploadFilePort,
    private val saveCompanyFilePort: SaveCompanyFilePort,
    private val saveResumeFilePort: SaveResumeFilePort,
    private val removeFilePort: RemoveFilePort,
    private val registerCompanyFilePort: RegisterCompanyFilePort,
): UploadCompanyFileUsecase, UploadResumeUsecase {

    @Async
    override fun uploadCompanyFile(file: MultipartFile, classification: CompanyFileClassificationType, companyId: String) {
        val fileId = UUID.randomUUID().toString()
        val dto = uploadFilePort.upload(file, "COMPANY/${companyId}", "${classification.name}/${fileId}")
        val companyFile = CompanyFile(
            fileId,
            dto,
            classification,
            companyId
        )
        if (classification == CompanyFileClassificationType.COMPANY_LOGO
            || classification == CompanyFileClassificationType.BUSINESS_CERTIFICATE
        ) {
            registerCompanyFilePort.register(
                RegisterCompanyFileDto(
                    fileId,
                    companyId,
                    classification
                )
            )
            removeFilePort.remove(fileId)
        }


        saveCompanyFilePort.save(
            companyFile
        )
    }

    @Async
    override fun uploadResume(resume: MultipartFile, noticeId: String) {
        val fileId = UUID.randomUUID().toString()
        val dto = uploadFilePort.upload(resume, "NOTICE/${noticeId}", "RESUME/${fileId}")
        val resume = Resume(
            fileId,
            dto,
            noticeId
        )

        saveResumeFilePort.save(resume)
    }
}