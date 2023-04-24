package com.info.info_v2_backend.notice.adapter.output.persistence.jdbc.configuration

import org.slf4j.LoggerFactory
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
    private val log = LoggerFactory.getLogger(this.javaClass)
    @Bean
    fun mysqlDataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName(
            env["spring.datasource.driver-class-name"] ?: throw java.lang.RuntimeException("데이터베이스 연결 오류")
        )
        println("This is Latest Docker Image!!!!!!!!!")
        dataSource.url = env["spring.datasource.url"]
        dataSource.username = env["spring.datasource.username"]
        dataSource.password = env["spring.datasource.password"]
        log.info("datasource url: ${dataSource.url}, username: ${dataSource.username}, password: ${dataSource.password}")
        return dataSource
    }
}