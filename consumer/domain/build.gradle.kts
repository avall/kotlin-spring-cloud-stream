plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")

	kotlin(Plugins.jvm)
	kotlin(Plugins.spring)
}

//apply {
//	plugin("org.springframework.boot")
//	plugin("java")
//	plugin("io.spring.dependency-management")
//}

dependencies {
	implementation(rootProject)
	implementation(project(ModulesApp.arch))
	implementation(Libraries.jacksonModuleKotlin)

	developmentOnly(SpringLibraries.springDevtools)
}

dependencyManagement {
	imports {
		mavenBom(SpringLibraries.springCloudDependencies)
//			mavenBom(SpringLibraries.springBootStarterParent)
	}
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
