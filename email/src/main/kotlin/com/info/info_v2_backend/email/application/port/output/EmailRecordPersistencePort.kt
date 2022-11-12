package com.info.info_v2_backend.email.application.port.output

import com.info.info_v2_backend.email.domain.EmailRecord

interface EmailRecordPersistencePort {

    fun save(emailRecord: EmailRecord): EmailRecord
}