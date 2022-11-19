package com.info.info_v2_backend.common.applies

enum class AppliesStatus(
    val meaning: String
) {
    WAITING("대기중"),
    APPROVE("승인됨"),
    REJECT("거부됨")

}