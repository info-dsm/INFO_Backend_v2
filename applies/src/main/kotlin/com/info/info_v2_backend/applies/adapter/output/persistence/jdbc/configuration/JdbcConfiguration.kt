package com.info.info_v2_backend.applies.adapter.output.persistence.jdbc.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource

@Configuration
class JdbcConfiguration(
    private val datasource: DataSource
) {

    @Bean
    fun jdbcTemplate(): JdbcTemplate {
        return JdbcTemplate(datasource)
    }
}