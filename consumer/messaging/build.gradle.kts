plugins {

	id("org.springframework.boot")

	kotlin(Plugins.jvm)
	kotlin(Plugins.spring)
}

dependencies {
	implementation(rootProject)

	implementation(SpringLibraries.springCloudStreamKafkaBinder)
	implementation(Libraries.jacksonDataTypeJsr310)
}


tasks.bootJar {
	enabled = false
//	archiveFileName.set("app.jar")
//	mainClass.set("com.avall.kotlin.ms.cousine.consumer.MSAttachmentApplication")
}

tasks.jar {
	enabled = true
//	archiveFileName.set("app.jar")
//	mainClass.set("com.avall.kotlin.ms.cousine.consumer.MSAttachmentApplication")
}

