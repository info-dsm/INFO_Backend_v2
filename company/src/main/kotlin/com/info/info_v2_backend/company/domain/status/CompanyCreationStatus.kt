package com.info.info_v2_backend.company.domain.status

enum class CompanyCreationStatus(
    val sequence: Int
) {

    WAITING(0),
    CREATED(1),
    FAILED(2)
}