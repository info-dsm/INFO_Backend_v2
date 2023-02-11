package com.info.info_v2_backend.file.adapter.output.persistence.repository

import com.info.info_v2_backend.file.domain.applies.Resume
import com.info.info_v2_backend.file.domain.applies.ResumeNotice
import com.info.info_v2_backend.file.domain.applies.ResumeStudent
import com.mysql.cj.protocol.x.Notice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface ResumeRepository: JpaRepository<Resume, String> {

    @Query(nativeQuery = true, value = "delete from resume where notice_id = :noticeId and resume_student_email = :resumeStudentEmail")
    fun deleteByNoticeAndStudent(@Param(value = "noticeId") noticeId: String, @Param(value = "resumeStudentEmail") studentEmail: String)

    @Query(nativeQuery = true, value = "select * from resume where notice_id = :noticeId and resume_student_email = :resumeStudentEmail")
    fun findByNoticeAndStudent(@Param(value = "noticeId") noticeId: String, @Param(value = "resumeStudentEmail") studentEmail: String): Optional<Resume>
}