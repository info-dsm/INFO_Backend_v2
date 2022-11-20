package com.info.info_v2_backend.applies.adapter.output.persistence.jdbc.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource

@Configuration
class DatasourceConfiguration(
    private val env: Environment
) {
    @Bean
    fun mysqlDataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName(
            env["spring.datasource.driver-class-name"] ?: throw java.lang.RuntimeException("데이터베이스 연결 오류")
        )
        dataSource.url = env["spring.datasource.url"]
        dataSource.username = env["spring.datasource.username"]
        dataSource.password = env["spring.datasource.password"]

        return dataSource
    }
}