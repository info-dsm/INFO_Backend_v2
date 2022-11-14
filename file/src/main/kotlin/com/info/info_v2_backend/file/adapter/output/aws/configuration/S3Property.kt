package com.info.info_v2_backend.file.adapter.output.aws.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("cloud.aws.s3")
@ConstructorBinding
data class S3Property (
    val bucketName: String
)
