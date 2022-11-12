package com.info.info_v2_backend.common.email

enum class EmailTemplateType(
    val templatePath: String,
) {
    NOTIFICATION("notification.html"),
    TEXT("none")

}