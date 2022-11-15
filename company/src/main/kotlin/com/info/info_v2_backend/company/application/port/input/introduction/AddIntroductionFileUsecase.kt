package com.info.info_v2_backend.company.application.port.input.introduction

import org.springframework.web.multipart.MultipartFile

interface AddIntroductionFileUsecase {

    fun add(file: MultipartFile, companyNumber: String): String
}