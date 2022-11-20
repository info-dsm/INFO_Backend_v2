package com.info.info_v2_backend.file.adapter.output.persistence.repository

import com.info.info_v2_backend.file.domain.applies.Resume
import com.info.info_v2_backend.file.domain.applies.ResumeNotice
import com.info.info_v2_backend.file.domain.applies.ResumeStudent
import org.springframework.data.jpa.repository.JpaRepository

interface ResumeRepository: JpaRepository<Resume, String> {

    fun deleteByNoticeAndStudent(notice: ResumeNotice, student: ResumeStudent)
}