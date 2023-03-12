package com.info.info_v2_backend.file.adapter.output.persistence.repository

import com.info.info_v2_backend.file.domain.notice.Attachment
import com.info.info_v2_backend.file.domain.notice.AttachmentNotice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface AttachmentRepository: JpaRepository<Attachment, String> {

    @Query(nativeQuery = true, value = "delete file, attachment from file inner join attachment on file.file_id = attachment.file_id where attachment.notice_id = :noticeId")
    fun deleteByNotice(@Param(value = "noticeId") noticeId: String)
    @Query(nativeQuery = true, value = "select * from file a, attachment b where b.notice_id = :noticeId and a.file_id = b.file_id")
    fun findByNotice(@Param(value = "noticeId") noticeId: String): List<Attachment>
}