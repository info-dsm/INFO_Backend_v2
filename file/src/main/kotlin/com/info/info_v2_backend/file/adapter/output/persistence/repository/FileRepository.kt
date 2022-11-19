package com.info.info_v2_backend.file.adapter.output.persistence.repository

import com.info.info_v2_backend.file.domain.File
import org.springframework.data.jpa.repository.JpaRepository

interface FileRepository: JpaRepository<File, String> {
}