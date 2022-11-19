package com.info.info_v2_backend.file.adapter.output.persistence.repository

import com.info.info_v2_backend.file.domain.applies.Resume
import org.springframework.data.jpa.repository.JpaRepository

interface ResumeRepository: JpaRepository<Resume, String> {
}