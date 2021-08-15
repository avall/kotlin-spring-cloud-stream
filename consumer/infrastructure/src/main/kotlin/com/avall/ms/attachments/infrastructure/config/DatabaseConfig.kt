package com.avall.ms.attachments.infrastructure.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableJpaRepositories(basePackages = ["com.avall.ms.attachments.infrastructure.adapters.database"])
@EnableTransactionManagement
@EntityScan("com.avall.ms.attachments.infrastructure.adapters.database")
class DatabaseConfig {}