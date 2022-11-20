package com.info.info_v2_backend.notice.application.port.input.change

interface ChangeCertificateUsecase {

    fun change(noticeId: String, certificateList: List<String>)
}