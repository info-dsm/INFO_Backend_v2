package com.info.info_v2_backend.file.adapter.output.persistence.repository

import com.info.info_v2_backend.file.domain.applies.Resume
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface ResumeRepository: JpaRepository<Resume, String> {

    @Query(nativeQuery = true, value = "delete file, resume from file inner join resume on file.file_id = resume.file_id where resume.notice_id = :noticeId and resume.resume_student_email = :resumeStudentEmail")
    fun deleteByNoticeAndStudent(@Param(value = "noticeId") noticeId: String, @Param(value = "resumeStudentEmail") studentEmail: String)

    @Query(nativeQuery = true, value = "select * from resume, file where notice_id = :noticeId and resume_student_email = :studentEmail and file.file_id = resume.file_id")
    fun findByNoticeIdAndStudentEmail(@Param(value = "noticeId") noticeId: String, @Param(value = "studentEmail") studentEmail: String): List<Resume>
}