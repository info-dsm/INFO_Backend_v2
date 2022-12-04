package com.info.info_v2_backend.notice.adapter.output.persistence.jdbc.mapper

import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.certificate.CertificateResponse
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class JdbcCertificateResponseMapper: RowMapper<CertificateResponse> {
    override fun mapRow(rs: ResultSet, rowNum: Int): CertificateResponse? {
        return try {
            CertificateResponse(
                rs.getString("certificate_id")
            )
        } catch (e: Exception) {
            null
        }
    }


}