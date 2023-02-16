package com.info.info_v2_backend.file.adapter.output.persistence.repository

import com.info.info_v2_backend.file.domain.applies.Resume
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface ResumeRepository: JpaRepository<Resume, String> {

    @Query(nativeQuery = true, value = "delete from resume where notice_id = :noticeId and resume_student_email = :resumeStudentEmail")
    fun deleteByNoticeAndStudent(@Param(value = "noticeId") noticeId: String, @Param(value = "resumeStudentEmail") studentEmail: String)

    fun findByNoticeIdAndStudentEmail(noticeId: String, studentEmail: String): Optional<Resume>
}