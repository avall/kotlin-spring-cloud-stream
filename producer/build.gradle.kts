import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("idea")
	id("org.springframework.boot") version Versions.springBoot
	id("io.spring.dependency-management") version Versions.springDependencyManagement
	id("org.sonarqube") version Versions.sonarQube
	id("org.jetbrains.kotlin.plugin.noarg") version Versions.kotlin
	kotlin(Plugins.jvm) version Versions.kotlin
	// kotlin-spring is a wrapper on top of all-open - https://kotlinlang.org/docs/all-open-plugin.html#spring-support
	kotlin(Plugins.spring) version Versions.kotlin
	// kotlin-jpa is wrapped on top of no-arg - https://kotlinlang.org/docs/no-arg-plugin.html#jpa-support
	kotlin(Plugins.jpa) version Versions.kotlin
}

java.sourceCompatibility = JavaVersion.VERSION_11

allprojects {
	group = "com.avall.kotlin.ms.cousine"
	version = "1.0.0"

	apply(plugin = "idea")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")
	apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
	apply(plugin = "org.sonarqube")
	apply(plugin = "org.jetbrains.kotlin.plugin.noarg")

	repositories {
		mavenCentral()
	}

	java.apply {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}

	dependencyManagement {
		imports {
			mavenBom("org.springframework.cloud:spring-cloud-dependencies:${Versions.springCloudVersion}")
		}
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "11"
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	tasks.bootJar { enabled = false }
	tasks.jar {enabled = true}

	dependencies {
		implementation(kotlin(Kotlin.reflect))
		implementation(kotlin(Kotlin.stdlibJdk8))
		implementation(Libraries.slf4j)

		testImplementation(Testlibraries.springBootStarterTest) {
			exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
			exclude(group = "junit", module = "junit")
		}
		testImplementation(Testlibraries.junitJupiterEngine)
		testImplementation(Testlibraries.junitJupiter)
		testImplementation(Testlibraries.mockitoKotlin)
		testImplementation(Testlibraries.striktCore)
		testImplementation(Testlibraries.springCloudStreamTestSupport)
		testImplementation(Testlibraries.springKafkaTest)
		testImplementation(Libraries.slf4j)

		testImplementation(Testlibraries.awaitility)

		runtimeOnly(Testlibraries.h2)

		annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	}
}