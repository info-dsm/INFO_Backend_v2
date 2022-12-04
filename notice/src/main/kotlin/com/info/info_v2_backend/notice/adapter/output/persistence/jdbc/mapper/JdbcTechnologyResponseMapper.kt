package com.info.info_v2_backend.notice.adapter.output.persistence.jdbc.mapper

import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.technology.TechnologyResponse
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class JdbcTechnologyResponseMapper: RowMapper<TechnologyResponse> {
    override fun mapRow(rs: ResultSet, rowNum: Int): TechnologyResponse? {
        return try {
            TechnologyResponse(
                rs.getString("technology_id")
            )
        } catch (e: Exception) {
            null
        }
    }

}