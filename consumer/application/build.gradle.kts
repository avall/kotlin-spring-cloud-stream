import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.gradle.api.tasks.wrapper.Wrapper.DistributionType.BIN
import org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES


springBoot {
	mainClass.set("com.avall.ms.attachments.MSAttachmentApplication")
}

plugins {
	id("org.springframework.cloud.contract") version Versions.springCloudContractPluginVersion
	id("com.gorylenko.gradle-git-properties") version "2.2.4"
	id("io.spring.dependency-management") // version Versions.springDependencyManagement
}

gitProperties {
    dateFormatTimeZone = "Europe/Berlin"
    failOnNoGitDirectory = false
}

contracts {
	testFramework.set(org.springframework.cloud.contract.verifier.config.TestFramework.JUNIT5)
    packageWithBaseClasses.set("com.avall.ms.attachments.apicontract")
    nameSuffixForTests.set("ContractTest")
//	contractsDslDir.file("${project.rootDir}/src/contractTest/resources/contracts/attachments")
//  generatedTestSourcesDir = project.file('src/generatedContract')
//	basePackageForTests.set("com.avall.ms.attachments.application.contract")
//    testMode.set(org.springframework.cloud.contract.verifier.config.TestMode.EXPLICIT)
}

dependencies {
	implementation(project(ModulesApp.arch))
	implementation(project(ModulesApp.api))
	implementation(project(ModulesApp.domain))
	implementation(project(ModulesApp.infrastructure))
	implementation(project(ModulesApp.messaging))

	implementation(Libraries.logstashEncoder)

	implementation(SpringLibraries.springBootStarterActuator)
	implementation(SpringLibraries.springBootStarter)
	implementation(SpringLibraries.springBootStarterWeb)
	implementation(SpringLibraries.springBootStarterSecurity)
	implementation(SpringLibraries.springSecurityOauth2ResourceServer)
	implementation(SpringLibraries.springSecurityOauth2Jose)
	implementation(Libraries.jacksonModuleKotlin)
	implementation(Libraries.logbook)
	implementation(SpringLibraries.springBootStarterDataJPa)

	implementation(Libraries.swaggerCore)

	runtimeOnly(Libraries.micrometer)

	developmentOnly(SpringLibraries.springDevtools)

	// https://github.com/spring-cloud/spring-cloud-contract/issues/1600#issuecomment-772158732
	testImplementation(Testlibraries.springCloudContractStarterVerifier) {
		exclude(group = "com.sun.xml.bind", module = "jaxb-osgi")
	}

	testImplementation(Testlibraries.springCloudContractSpecKotlin)
	testImplementation(Kotlin.kotlinCompilerEmbedable)
}

the<DependencyManagementExtension>().apply {
	imports {
		mavenBom(BOM_COORDINATES)
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${Versions.springCloudVersion}")
		mavenBom("org.springframework.cloud:spring-cloud-contract-dependencies:${Versions.springCloudStarterContractVerifierVersion}")
	}
}

tasks.bootJar {
	enabled = true
}

tasks.jar {
	enabled = true
	archiveFileName.set("app.jar")
}

tasks.withType<Wrapper>().configureEach {
	gradleVersion = "7.1"
	distributionType = BIN
}

tasks {
	contractTest {
		useJUnitPlatform()
		systemProperty("spring.profiles.active", "gradle")
		testLogging {
			exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
		}
		afterSuite(KotlinClosure2({ desc: TestDescriptor, result: TestResult ->
			if (desc.parent == null) {
				if (result.testCount == 0L) {
					throw IllegalStateException("No tests were found. Failing the build")
				}
				else {
					println("Results: (${result.testCount} tests, ${result.successfulTestCount} successes, ${result.failedTestCount} failures, ${result.skippedTestCount} skipped)")
				}
			} else { /* Nothing to do here */ }
		}))
	}
}
