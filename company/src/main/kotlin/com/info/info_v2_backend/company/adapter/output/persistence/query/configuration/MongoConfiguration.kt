package com.info.info_v2_backend.company.adapter.output.persistence.query.configuration

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration

@Configuration
class MongoConfiguration(
    private val datasourceProperty: DataSourceProperty,
) : AbstractMongoClientConfiguration() {



    override fun getDatabaseName(): String {
        return "info-v1"
    }

    override fun mongoClient(): MongoClient {
        val connectionString = ConnectionString(datasourceProperty.mongo.url)
        val mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()
        return MongoClients.create(mongoClientSettings)
    }

    override fun autoIndexCreation(): Boolean {
        return true
    }

}