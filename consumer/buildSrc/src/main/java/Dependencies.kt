object ApplicationId {
    const val id = "com.oneinsurance.oneapp"
}

object Releases {
    const val versionCode = 540
    const val versionName = "5.4.0"
}

object Plugins {
    const val jvm = "jvm"
    const val spring = "plugin.spring"
    const val jpa = "plugin.jpa"
}

object Versions {
    // System
    const val kotlin = "1.5.21"
    const val gradle = "3.5.2"
    const val jvmTarget = "11"

    // Application
    const val springBoot = "2.5.2"
    const val springDependencyManagement = "1.0.10.RELEASE"
    const val springCloudStarterContractVerifierVersion = "3.0.3"
    const val springCloudContractSpecKotlinVersion = "3.0.3"
    const val springCloudContractPluginVersion = "3.0.3"
    const val springCloudVersion = "2020.0.3"
    const val springDocVersion = "1.5.2"
    const val swaggerVersion = "2.1.6"
    const val striktVersion = "0.28.1"
    const val testcontainersVersion = "1.15.1"
    const val mavenFailsafePluginVersion = "2.22.2"
    const val sonarMavenPluginVersion = "3.7.0.1746"
    const val jacocoMavenPluginVersion = "0.8.6"
    const val logbookVersion = "2.0.0"
    const val restAssuredVersion = "4.3.2"
    const val logstashLogbackEncoderVersion = "6.3"
    const val mockitoKotlinVersion = "3.2.0"
    const val slf4jVersion = "1.7.30"
    const val sonarQube = "3.2.0"

}


object Kotlin {
    const val reflect   = "reflect"
    const val stdlibJdk8 = "stdlib-jdk8"
    const val kotlinCompilerEmbedable = "org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable:${Versions.kotlin}"
}

object Libraries {
    const val jacksonModuleKotlin = "com.fasterxml.jackson.module:jackson-module-kotlin"
    const val springDocUi = "org.springdoc:springdoc-openapi-ui:${Versions.springDocVersion}"
    const val springDocSecurity = "org.springdoc:springdoc-openapi-security:${Versions.springDocVersion}"
    const val springDocDataRest = "org.springdoc:springdoc-openapi-data-rest:${Versions.springDocVersion}"
    const val springDocKotlin = "org.springdoc:springdoc-openapi-kotlin:${Versions.springDocVersion}"
    const val swaggerCore = "io.swagger.core.v3:swagger-core:${Versions.swaggerVersion}"
    const val logbook = "org.zalando:logbook-spring-boot-starter:${Versions.logbookVersion}"
    const val logStash = "net.logstash.logback:logstash-logback-encoder:${Versions.logstashLogbackEncoderVersion}"
    const val postgress = "org.postgresql:postgresql"
    const val slf4j = "org.slf4j:slf4j-api:${Versions.slf4jVersion}"
    const val jacksonDataTypeJsr310 = "com.fasterxml.jackson.datatype:jackson-datatype-jsr310"
    const val logstashEncoder = "net.logstash.logback:logstash-logback-encoder:${Versions.logstashLogbackEncoderVersion}"
    const val micrometer = "io.micrometer:micrometer-registry-prometheus"
}

object SpringLibraries {
    const val springCloudDependencies = "org.springframework.cloud:spring-cloud-dependencies:${Versions.springCloudVersion}"
    const val springBootStarterDataJPa = "org.springframework.boot:spring-boot-starter-data-jpa"
    const val springDevtools = "org.springframework.boot:spring-boot-devtools"
    const val springCloudStreamKafkaBinder = "org.springframework.cloud:spring-cloud-stream-binder-kafka"
    const val springBootStarterActuator = "org.springframework.boot:spring-boot-starter-actuator"
    const val springBootStarterWeb = "org.springframework.boot:spring-boot-starter-web"
    const val springBootStarterSecurity = "org.springframework.boot:spring-boot-starter-security"
    const val springBootStarter = "org.springframework.boot:spring-boot-starter"
    const val springSecurityOauth2ResourceServer = "org.springframework.security:spring-security-oauth2-resource-server"
    const val springSecurityOauth2Jose = "org.springframework.security:spring-security-oauth2-jose"
    const val springBootStarterParent = "org.springframework.boot:spring-boot-starter-parent:${Versions.springBoot}"
    const val springCloudContractDependencies = "org.springframework.cloud:spring-cloud-contract-dependencies:${Versions.springCloudContractPluginVersion}"
    const val springWeb = "org.springframework:spring-web"
}

object Testlibraries {
//    const val springCloudContractVerifier = "org.springframework.cloud:spring-cloud-starter-contract-verifier:${Versions.springCloudStarterContractVerifierVersion}"
//    const val springCloudContractSpecKotlin = "org.springframework.cloud:spring-cloud-contract-spec-kotlin:${Versions.springCloudContractSpecKotlinVersion}"
    const val springCloudContractStarterVerifier = "org.springframework.cloud:spring-cloud-starter-contract-verifier"
    const val springCloudContractSpecKotlin = "org.springframework.cloud:spring-cloud-contract-spec-kotlin"
    const val springwebtestClient = "io.rest-assured:spring-web-test-client:${Versions.restAssuredVersion}"
    const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlinVersion}"
    const val springBootStarterTest = "org.springframework.boot:spring-boot-starter-test"
    const val junitJupiter = "org.junit.jupiter:junit-jupiter"
    const val junitJupiterEngine = "org.junit.jupiter:junit-jupiter-engine"
    const val testContainerJupiter = "org.testcontainers:junit-jupiter:${Versions.testcontainersVersion}"
    const val testContainerPostgresql = "org.testcontainers:postgresql:${Versions.testcontainersVersion}"
    const val striktCore = "io.strikt:strikt-core:${Versions.striktVersion}"
    const val striktJavaTime = "io.strikt:strikt-java-time:${Versions.striktVersion}"
    const val striktJackson = "io.strikt:strikt-jackson:${Versions.striktVersion}"
    const val restAssured = "io.rest-assured:spring-web-test-client:${Versions.restAssuredVersion}"
    const val springCloudStreamTestSupport = "org.springframework.cloud:spring-cloud-stream-test-support"
    const val springKafkaTest = "org.springframework.kafka:spring-kafka-test"
    const val awaitility = "org.awaitility:awaitility"
    const val h2 = "com.h2database:h2"
}

object ModulesApp {
    const val arch              = ":arch"
    const val api               = ":api"
    const val domain            = ":domain"
    const val messaging         = ":messaging"
    const val application       = ":application"
    const val infrastructure    = ":infrastructure"
}
