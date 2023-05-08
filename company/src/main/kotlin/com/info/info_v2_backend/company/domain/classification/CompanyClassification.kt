package com.info.info_v2_backend.company.domain.classification

enum class CompanyClassification(
    val meaning: String,
    val description: String
) {

    STARTUP("스타트업", "설립연도 10년 이내이면서 매출액 100억 이하이고 직원 수 30 이하인 기업"),
    LARGE_SCALE("규모가 큰 기업", "매출액 200억 이상이고 직원 수 50 이상인 기업")

}
