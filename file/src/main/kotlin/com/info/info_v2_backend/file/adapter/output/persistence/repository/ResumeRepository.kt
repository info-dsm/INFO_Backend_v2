package com.info.info_v2_backend.file.adapter.output.persistence.repository

import com.info.info_v2_backend.file.domain.applies.Resume
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

interface ResumeRepository: JpaRepository<Resume, String> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update file, resume set file.file_is_deleted = 'true' where file.file_id = resume.file_id and resume.notice_id = :noticeId and resume.resume_student_email = :resumeStudentEmail")
    fun deleteByNoticeAndStudent(@Param(value = "noticeId") noticeId: String, @Param(value = "resumeStudentEmail") studentEmail: String)

    @Query(nativeQuery = true, value = "select * from resume, file where notice_id = :noticeId and resume_student_email = :studentEmail and file.file_id = resume.file_id and file_is_deleted = false")
    fun findByNoticeIdAndStudentEmail(@Param(value = "noticeId") noticeId: String, @Param(value = "studentEmail") studentEmail: String): List<Resume>
}