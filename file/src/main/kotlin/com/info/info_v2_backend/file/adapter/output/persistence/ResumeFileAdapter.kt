package com.info.info_v2_backend.file.adapter.output.persistence

import com.info.info_v2_backend.file.adapter.output.persistence.repository.ResumeRepository
import com.info.info_v2_backend.file.application.port.output.save.SaveResumeFilePort
import com.info.info_v2_backend.file.domain.applies.Resume
import org.springframework.stereotype.Service

@Service
class ResumeFileAdapter(
    private val resumeRepository: ResumeRepository
): SaveResumeFilePort {
    override fun save(resume: Resume) {
        resumeRepository.save(resume)
    }
}