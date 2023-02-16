package com.info.info_v2_backend.file.adapter.output.persistence

import com.info.info_v2_backend.file.adapter.output.persistence.repository.ResumeRepository
import com.info.info_v2_backend.file.application.port.output.applies.LoadResumePort
import com.info.info_v2_backend.file.application.port.output.applies.RemoveResumePort
import com.info.info_v2_backend.file.application.port.output.applies.SaveResumeFilePort
import com.info.info_v2_backend.file.domain.applies.Resume
import org.springframework.stereotype.Service

@Service
class ResumeFileAdapter(
    private val resumeRepository: ResumeRepository
): SaveResumeFilePort, RemoveResumePort, LoadResumePort {
    override fun save(resume: Resume) {
        resumeRepository.save(resume)
    }
    override fun remove(noticeId: String, studentEmail: String) {
        resumeRepository.deleteByNoticeAndStudent(noticeId, studentEmail)
    }

    override fun load(noticeId: String, studentEmail: String): Resume? {
        return resumeRepository.findByNoticeIdAndStudentEmail(
            noticeId,
            studentEmail
        ).orElse(null)
    }
}