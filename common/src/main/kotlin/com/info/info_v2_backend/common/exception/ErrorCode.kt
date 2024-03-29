package com.info.info_v2_backend.common.exception

enum class ErrorCode(
    val status: Int,
    val code: String,
    val message: String
) {
    //MaximumError: C17 - 2023-03-19 기준

    //Server Error
    FRAME_WORK_INTERNAL_ERROR(500, "C01", "프레임워크 내부적인 오류가 발생했습니다."),
    UNDEFINED_ERROR(500, "C02", "예상치 못한 오류가 발생했습니다."),
    BAD_GATEWAY_ERROR(502, "C04", "작업 수행 과정 중 발생한 통신에서 문제가 발생했습니다."),

    //Bad Request
    MULTIPART_FILE_ERROR(400, "C03", "MultipartFile을 업로드 하는 과정에서 문제가 발생했습니다. File 크기를 확인하거나 확장자, Content-type을 확인하세요."),
    NO_DATA_FOUND_ERROR(400, "C06", "데이터를 찾지 못했습니다."),
    ALREADY_EXISTS_ERROR(400, "C08", "이미 존재하는 데이터입니다."),
    NOT_MATCHED_ERROR(400, "C10", "데이터가 일치하지 않습니다."),
    INVALID_INPUT_DATA_ERROR(400, "C12", "입력값이 올바르지 않습니다."),
    FEIGN_ERROR(400, "C17", "내부 통신 과정 중 오류 발생"),

    //Not Found
    INPUT_DATA_NOT_FOUND_ERROR(404, "C05", "입력값을 찾지 못했습니다."),
    PERSISTENCE_DATA_NOT_FOUND_ERROR(404, "C09", "저장된 값을 찾지 못했습니다."),

    //Unauthorized
    TOKEN_NEED_ERROR(401, "C14", "토큰이 필요합니다."),
    INVALID_TOKEN_ERROR(401, "C11", "올바르지 않은 토큰입니다."),
    EXPIRED_TOKEN_ERROR(401, "C13", "토큰이 만료되었습니다."),

    //Forbidden
    NO_AUTHORIZATION_ERROR(403, "C16", "권한이 부족합니다.")



}
