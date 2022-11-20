package com.info.info_v2_backend.applies.adapter.output.persistence.jdbc

import com.info.info_v2_backend.applies.domain.Applies
import com.info.info_v2_backend.applies.domain.notice.AppliesNotice
import com.info.info_v2_backend.applies.domain.user.Applicant
import com.info.info_v2_backend.common.applies.AppliesStatus
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class AppliesMapper: RowMapper<Applies> {

    override fun mapRow(rs: ResultSet, rowNum: Int): Applies? {
        try {
            val applies = Applies(
                rs.getString("id"),
                Applicant(
                    rs.getString("applicant_email"),
                    rs.getString("applicant_name"),
                    rs.getInt("applicant_entrance_year")
                ),
                AppliesNotice(
                    rs.getString("applies_notice_id")
                ),
                rs.getDate("created_at").toLocalDate(),
                rs.getDate("updated_at").toLocalDate(),
                AppliesStatus.valueOf(rs.getString("applies_status"))
            )
            return applies
        } catch (e: Exception) {
            return null
        }
    }


}