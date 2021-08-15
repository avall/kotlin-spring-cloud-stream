plugins {
	id("org.springframework.boot")
	id("org.jetbrains.kotlin.plugin.allopen")

	kotlin(Plugins.jvm)
	kotlin(Plugins.spring)
	kotlin(Plugins.jpa)
}

allOpen {
	annotations(
		"javax.persistence.Entity",
		"javax.persistence.MappedSuperclass",
		"javax.persistence.Embeddable"
	)
}

dependencies {
	implementation(rootProject)
	implementation(project(ModulesApp.arch))
	implementation(project(ModulesApp.domain))
	implementation(project(ModulesApp.messaging))

	implementation(SpringLibraries.springBootStarterDataJPa)

	implementation(Libraries.jacksonModuleKotlin)
	implementation(Libraries.logbook)
	runtimeOnly(Libraries.postgress)
	runtimeOnly(Testlibraries.h2)

	testImplementation(Testlibraries.testContainerJupiter)
	testImplementation(Testlibraries.testContainerPostgresql)
	testImplementation(Libraries.logstashEncoder)
	developmentOnly(SpringLibraries.springDevtools)
}

tasks.bootJar {
	enabled = false
//	archiveFileName.set("app.jar")
//	mainClass.set("com.avall.ms.attachments.MSAttachmentApplication")
}

tasks.jar {
	enabled = true
//	archiveFileName.set("app.jar")
//	mainClass.set("com.avall.ms.attachments.MSAttachmentApplication")
}
