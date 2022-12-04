package com.info.info_v2_backend.file.adapter.output.persistence.repository

import com.info.info_v2_backend.file.domain.applies.Resume
import com.info.info_v2_backend.file.domain.applies.ResumeNotice
import com.info.info_v2_backend.file.domain.applies.ResumeStudent
import com.mysql.cj.protocol.x.Notice
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ResumeRepository: JpaRepository<Resume, String> {

    fun deleteByNoticeAndStudent(notice: ResumeNotice, student: ResumeStudent)
    fun findByNoticeAndStudent(notice: ResumeNotice, student: ResumeStudent): Optional<Resume>
}