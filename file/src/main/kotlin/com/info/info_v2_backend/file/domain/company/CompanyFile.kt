package com.info.info_v2_backend.file.domain.company

import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.dto.FileDto
import com.info.info_v2_backend.common.file.dto.response.CompanyFileResponse
import com.info.info_v2_backend.file.domain.File
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("company_file")
@OnDelete(action = OnDeleteAction.CASCADE)
class CompanyFile(
    id: String,
    fileDto: FileDto,
    companyFileType: CompanyFileClassificationType,
    companyNumber: String
): File(
    id,
    fileDto.fileUrl,
    fileDto.fileType,
    fileDto.extension,
    fileDto.fileName
) {

    @Column(name = "company_number", nullable = false)
    val companyNumber: String = companyNumber

    @Column(name = "company_file_classification", nullable = false)
    val companyFileClassification: CompanyFileClassificationType = companyFileType

    fun toCompanyFileResponse(): CompanyFileResponse {
        return CompanyFileResponse(
            this.id,
            this.fileUrl,
            this.fileContentType,
            this.extension,
            this.fileName,
            this.companyNumber,
            this.companyFileClassification
        )
    }

}