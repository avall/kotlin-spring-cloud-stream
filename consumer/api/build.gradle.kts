
dependencies {
	implementation(rootProject)

	implementation(Libraries.jacksonModuleKotlin)
	implementation(Libraries.swaggerCore)
	implementation(SpringLibraries.springWeb)
	implementation(SpringLibraries.springBootStarterTomcat)
	implementation(SpringLibraries.springBootStarterSecurity)

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
