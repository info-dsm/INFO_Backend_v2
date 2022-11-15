package com.info.info_v2_backend.file.domain.company

import com.info.info_v2_backend.common.file.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.FileDto
import com.info.info_v2_backend.file.domain.File
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("company_introduction_file")
@OnDelete(action = OnDeleteAction.CASCADE)
class CompanyFile(
    id: String,
    fileDto: FileDto,
    companyFileType: CompanyFileClassificationType,
    companyId: String
): File(
    id,
    fileDto.fileUrl,
    fileDto.fileType,
    fileDto.extension,
    fileDto.fileName
) {

    @Column(name = "file_company_id", nullable = false)
    val companyId: String = companyId

    @Column(name = "company_file_classification", nullable = false)
    val companyFileClassification: CompanyFileClassificationType = companyFileType

}