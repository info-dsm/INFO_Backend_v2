package com.info.info_v2_backend.file.adapter.output.persistence.repository

import com.info.info_v2_backend.file.domain.notice.Attachment
import com.info.info_v2_backend.file.domain.notice.AttachmentNotice
import org.springframework.data.jpa.repository.JpaRepository

interface AttachmentRepository: JpaRepository<Attachment, String> {

    fun deleteByNotice(notice: AttachmentNotice)
}