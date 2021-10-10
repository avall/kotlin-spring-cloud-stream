package com.avall.kotlin.ms.cousine.consumer.infrastructure

import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers


@Testcontainers
abstract class PostgreSqlContainer {

    companion object {

        @Container
        protected val container = PostgreSQLContainer<Nothing>("postgres:12.4-alpine").apply {
            withDatabaseName("Attachment-test-db")
            withUsername("Attachment-test-db-user")
            withPassword("Attachment-test-db-password")
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url") { "jdbc:postgresql://${container.host}:${container.firstMappedPort}/${container.databaseName}" }
            registry.add("spring.datasource.username") { container.username }
            registry.add("spring.datasource.password") { container.password }
        }
    }
}