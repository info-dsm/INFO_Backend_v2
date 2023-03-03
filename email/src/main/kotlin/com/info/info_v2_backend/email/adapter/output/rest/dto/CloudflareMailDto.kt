package com.info.info_v2_backend.email.adapter.output.rest.dto

data class CloudflareMailDto (
    val personalizations: List<CloudflareTargetDto>,
    val from: CloudflareUserDto,
    val subject: String,
    val content: List<CloudflareContentDto>
) {
    data class CloudflareTargetDto(
        val to: List<CloudflareUserDto>
    )

    data class CloudflareUserDto (
        val email: String,
        val name: String? = email
    )

    data class CloudflareContentDto (
        val type: String? = "text/plain",
        val value: String

    )

}