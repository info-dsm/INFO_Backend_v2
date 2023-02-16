package com.info.info_v2_backend.common.file.dto.type

enum class FileType(val value: Int) {
    IMAGE(0),
    DOCS(1),
    UNKNOWN(2);


    companion object {
        fun fromInt(value: Int) = FileType.values().first { it.value == value }
    }
}