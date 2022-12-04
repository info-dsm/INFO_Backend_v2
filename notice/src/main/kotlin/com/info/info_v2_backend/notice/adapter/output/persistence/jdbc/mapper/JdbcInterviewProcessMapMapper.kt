package com.info.info_v2_backend.notice.adapter.output.persistence.jdbc.mapper

import com.info.info_v2_backend.notice.domain.interview.InterviewProcess
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class JdbcInterviewProcessMapMapper: RowMapper<Map<Int, InterviewProcess>> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Map<Int, InterviewProcess>? {
        try {
            val map: MutableMap<Int, InterviewProcess> = HashMap()

            rs.getString("interview_process_sequence")
            rs.getString("interview_process_map")
            return map
        } catch (e: Exception) {
            return null
        }
    }
}