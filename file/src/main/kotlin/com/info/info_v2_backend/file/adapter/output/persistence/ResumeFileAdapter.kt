package com.info.info_v2_backend.file.adapter.output.persistence

import com.info.info_v2_backend.file.adapter.output.persistence.repository.ResumeRepository
import com.info.info_v2_backend.file.application.port.output.applies.LoadResumePort
import com.info.info_v2_backend.file.application.port.output.applies.RemoveResumePort
import com.info.info_v2_backend.file.application.port.output.applies.SaveResumeFilePort
import com.info.info_v2_backend.file.domain.applies.Resume
import com.info.info_v2_backend.file.domain.applies.ResumeNotice
import com.info.info_v2_backend.file.domain.applies.ResumeStudent
import org.springframework.stereotype.Service

@Service
class ResumeFileAdapter(
    private val resumeRepository: ResumeRepository
): SaveResumeFilePort, RemoveResumePort, LoadResumePort {
    override fun save(resume: Resume) {
        resumeRepository.save(resume)
    }
    override fun remove(notice: ResumeNotice, student: ResumeStudent) {
        resumeRepository.deleteByNoticeAndStudent(notice, student)
    }

    override fun load(noticeId: String, studentEmail: String): Resume? {
        return resumeRepository.findByNoticeAndStudent(
            ResumeNotice(
                noticeId
            ),
            ResumeStudent(
                studentEmail
            )
        ).orElse(null)
    }
}