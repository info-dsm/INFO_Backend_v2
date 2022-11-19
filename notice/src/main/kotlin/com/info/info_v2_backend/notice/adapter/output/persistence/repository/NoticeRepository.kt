package com.info.info_v2_backend.notice.adapter.output.persistence.repository

import com.info.info_v2_backend.notice.domain.Notice
import org.springframework.data.jpa.repository.JpaRepository

interface NoticeRepository: JpaRepository<Notice, String> {

}