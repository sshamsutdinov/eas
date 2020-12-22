package ru.eas.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.gridfs.GridFsTemplate

@Configuration
open class MongoConfiguration : AbstractMongoClientConfiguration() {

    @Value("\${mongo-url}")
    private lateinit var mongoUrl: String

    @Value("\${mongo-db-name}")
    private lateinit var mongoDbName: String

    @Bean
    override fun mongoClient(): MongoClient {
        val connectionString = ConnectionString(mongoUrl)
        val mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()
        return MongoClients.create(mongoClientSettings)
    }


    @Bean
    open fun gridFsTemplate(
        mongoDbFactory: MongoDatabaseFactory,
        mappingMongoConverter: MappingMongoConverter
    ) = GridFsTemplate(mongoDbFactory, mappingMongoConverter)

    override fun getDatabaseName(): String = mongoDbName
}