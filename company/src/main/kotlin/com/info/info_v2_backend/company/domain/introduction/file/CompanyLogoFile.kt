package com.info.info_v2_backend.company.domain.introduction.file

import com.info.info_v2_backend.commonEntity.file.File
import com.info.info_v2_backend.commonEntity.file.FileDto
import com.info.info_v2_backend.company.domain.Company
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*


@Entity
@DiscriminatorValue("company_logo_file")
@OnDelete(action = OnDeleteAction.CASCADE)
class CompanyLogoFile(
    dto: FileDto,
    company: Company
): File(
    dto.fileUrl,
    dto.fileType,
    dto.extension,
    dto.fileName
) {

    @OneToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "company_id", nullable = false)
    var company: Company = company
        protected set


    override fun toString(): String {
        return "url: ${this.fileUrl}, companyId: ${this.company.companyName.companyNumber!!}, fileType: ${this.fileContentType}, " +
                "extension: ${this.extension}"
    }

}