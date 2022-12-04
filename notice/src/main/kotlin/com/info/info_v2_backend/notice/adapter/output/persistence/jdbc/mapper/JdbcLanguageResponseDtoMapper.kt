package com.info.info_v2_backend.notice.adapter.output.persistence.jdbc.mapper

import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.LanguageResponse
import com.info.info_v2_backend.notice.adapter.output.persistence.jdbc.dto.LanguageUsageDto
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class JdbcLanguageResponseDtoMapper: RowMapper<LanguageResponse> {
    override fun mapRow(rs: ResultSet, rowNum: Int): LanguageResponse? {
        return try {
            LanguageResponse(
                rs.getString("language_id")
            )
        } catch (e: Exception) {
            null
        }
    }


}