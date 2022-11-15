package com.info.info_v2_backend.company.application.port.input

import org.springframework.web.multipart.MultipartFile

interface ChangeBusinessCertificatePort {

    fun change(businessCertificate: MultipartFile, companyNumber: String): String
}