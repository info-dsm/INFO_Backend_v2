package com.info.info_v2_backend.applies.adapter.output.persistence

import com.info.info_v2_backend.applies.adapter.output.persistence.jdbc.AppliesMapper
import com.info.info_v2_backend.applies.application.port.output.cancel.CancelApplyPort
import com.info.info_v2_backend.applies.application.port.output.load.LoadAppliesPort
import com.info.info_v2_backend.applies.domain.Applies
import com.info.info_v2_backend.common.applies.AppliesStatus
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class AppliesJdbcAdapter(
    private val jdbcTemplate: JdbcTemplate
): LoadAppliesPort, CancelApplyPort {

    override fun loadAppliesList(noticeId: String, status: AppliesStatus): List<Applies> {
        val query = "select * from applies where applies_notice_id = \"$noticeId\" and applies_status = \"${status.name}\""
        val result = jdbcTemplate.query(
            query,
            AppliesMapper()
        )
        return result
    }

    override fun loadApplies(appliesId: String): Applies? {
        val query = "select * from applies where id = \"$appliesId\" limit 1;"
        val result = jdbcTemplate.query(
            query,
            AppliesMapper()
        )
        return result[0]
    }

    override fun loadAppliesByNoticeAndStudentEmail(noticeId: String, studentEmail: String): Applies? {
        val query = "select * from applies where applies_notice_id = \"$noticeId\" and applicant_email = \"$studentEmail\" limit 1;"
        val result = jdbcTemplate.query(
            query,
            AppliesMapper()
        )
        return result[0]
    }

    override fun cancelApply(noticeId: String, studentEmail: String) {
        val query = "delete from applies where applies_notice_id = \"$noticeId\" and applicant_email = \"$studentEmail\";"
        jdbcTemplate.execute(query)
    }
}