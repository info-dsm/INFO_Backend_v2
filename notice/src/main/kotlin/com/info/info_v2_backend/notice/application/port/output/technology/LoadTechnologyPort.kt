package com.info.info_v2_backend.notice.application.port.output.technology

import com.info.info_v2_backend.notice.domain.technology.Technology

interface LoadTechnologyPort {

    fun load(technologyName: String): Technology?
}