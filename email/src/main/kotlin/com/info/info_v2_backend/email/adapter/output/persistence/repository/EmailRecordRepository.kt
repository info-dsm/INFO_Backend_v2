package com.info.info_v2_backend.email.adapter.output.persistence.repository

import com.info.info_v2_backend.email.domain.EmailRecord
import org.springframework.data.jpa.repository.JpaRepository

interface EmailRecordRepository: JpaRepository<EmailRecord, String> {

}