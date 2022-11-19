package com.info.info_v2_backend.common.file.dto.type

enum class ImageExt(
    val extension: String,
    val contentType: String
) {
    JPG("jpg", "image/jpeg"),
    JPEG("jpeg", "image/jpeg"),
    PNG("png", "image/png")
}