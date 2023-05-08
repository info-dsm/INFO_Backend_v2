package com.info.info_v2_backend.company.adapter.output.persistence.mongo.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("datasource")
@ConstructorBinding
data class DataSourceProperty (
    val mongo: NosqlSourceProperty

)

data class NosqlSourceProperty(
    val url: String
)
