package com.avall.ms.attachments.application.config;

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.zalando.logbook.BodyFilter
import org.zalando.logbook.BodyFilters
import org.zalando.logbook.json.JsonBodyFilters

@Configuration
@ConfigurationProperties(prefix = "logbook.obfuscate")
open class LoggerConfig {
    var body: List<String>? = null

    @Bean
    open fun bodyFilter(): BodyFilter {
        return BodyFilter.merge(
            BodyFilters.defaultValue(),
            JsonBodyFilters.replaceJsonStringProperty(HashSet(body), LOGBOOK_BODY_REPLACEMENT)
        )
    }

    companion object {
        const val LOGBOOK_BODY_REPLACEMENT = "XXX"
    }


}
