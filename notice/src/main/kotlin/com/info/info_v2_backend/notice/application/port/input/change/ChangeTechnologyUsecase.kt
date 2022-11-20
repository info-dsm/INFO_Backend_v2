package com.info.info_v2_backend.notice.application.port.input.change

interface ChangeTechnologyUsecase {

    fun change(noticeId: String, technologyList: List<String>)
}