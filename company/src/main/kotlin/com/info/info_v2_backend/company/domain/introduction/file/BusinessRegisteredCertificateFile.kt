package com.info.info_v2_backend.company.domain.introduction.file

import com.info.info_v2_backend.commonEntity.file.File
import com.info.info_v2_backend.commonEntity.file.FileDto
import com.info.info_v2_backend.company.domain.Company
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
@DiscriminatorValue("business_registered_certificate_file")
@OnDelete(action = OnDeleteAction.CASCADE)
class BusinessRegisteredCertificateFile(
    dto: FileDto,
    company: Company
): File(
    dto.fileUrl,
    dto.fileType,
    dto.extension,
    dto.fileName
){

    @OneToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "company_id")
    var company: Company = company
        protected set

}