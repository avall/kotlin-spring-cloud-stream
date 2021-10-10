package com.avall.kotlin.ms.cousine.consumer.infrastructure.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableJpaRepositories(basePackages = ["com.avall.kotlin.ms.cousine.consumer.infrastructure.adapters.database"])
@EnableTransactionManagement
@EntityScan("com.avall.kotlin.ms.cousine.consumer.infrastructure.adapters.database")
class DatabaseConfig {}