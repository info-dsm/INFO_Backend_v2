package com.info.info_v2_backend.email.adapter.output.rest.dto

data class CloudflareMailDto (
    val body: ClouddflareBodyDto
) {

    data class ClouddflareBodyDto(
        val to: CloudflareUserDto,
        val from: CloudflareMailDto
    )

    data class CloudflareUserDto (
        val email: String,
        val name: String
    )

}