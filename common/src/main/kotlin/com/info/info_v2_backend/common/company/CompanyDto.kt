package com.info.info_v2_backend.common.company

data class CompanyDto (
    val companyNumber: String,
    val companyName: String,
    val companyPhone: String,
    val contactorEmail: String,
    val companyIntroduction: String,
    val isLeading: Boolean

)