plugins {
	id("org.springframework.boot")
	id("org.jetbrains.kotlin.plugin.allopen")
//	id("org.liquibase.gradle") version "2.0.4"

	kotlin(Plugins.jvm)
	kotlin(Plugins.spring)
	kotlin(Plugins.jpa)
}

//allOpen {
//	annotations(
//		"javax.persistence.Entity",
//		"javax.persistence.MappedSuperclass",
//		"javax.persistence.Embeddable"
//	)
//}

dependencies {
	implementation(rootProject)
	implementation(project(ModulesApp.arch))
	implementation(project(ModulesApp.domain))
	implementation(project(ModulesApp.messaging))

//	implementation(SpringLibraries.springBootStarterDataJPa)
//	implementation(SpringLibraries.springBootStarterSecurity)
	implementation(SpringLibraries.springBootStarterWeb)
	implementation(SpringLibraries.springBootStarterTomcat)
	implementation(Libraries.customJwtToken)

	implementation(Libraries.jacksonModuleKotlin)


//	implementation(Libraries.liquibaseCore)
//	implementation(Libraries.liquibasePlugin)
//	liquibaseRuntime(Libraries.liquibaseHibernate)

	implementation(Libraries.logbook)
//	runtimeOnly(Libraries.postgress)
//	runtimeOnly(Testlibraries.h2)

	testImplementation(Testlibraries.testContainerJupiter)
//	testImplementation(Testlibraries.testContainerPostgresql)
	testImplementation(Libraries.logstashEncoder)
	developmentOnly(SpringLibraries.springDevtools)
}

tasks.bootJar {
	enabled = false
//	archiveFileName.set("app.jar")
//	mainClass.set("com.avall.kotlin.ms.cousine.producer.MSAttachmentApplication")
}

tasks.jar {
	enabled = true
//	archiveFileName.set("app.jar")
//	mainClass.set("com.avall.kotlin.ms.cousine.producer.MSAttachmentApplication")
}

//tasks.register("liquibase") {
//	// depend on the liquibase status task
//	dependsOn("run")
//	liquibase {
//		activities.register("main") {
//			this.arguments = mapOf(
//				"logLevel" to "info",
//				"changeLogFile" to "src/main/resources/db/changelog/changelog-master.xml",
//				"url" to "jdbc:h2:mem:qamyapp",
//				"username" to "sa",
//				"password" to "")
//		}
//	}
//}