package com.info.info_v2_backend.file.application.port.output.save

import com.info.info_v2_backend.file.domain.applies.Resume

interface SaveResumeFilePort {

    fun save(resume: Resume)
}