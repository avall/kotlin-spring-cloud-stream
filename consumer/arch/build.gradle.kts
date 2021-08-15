
plugins {
	kotlin(Plugins.jvm)
}

dependencies {
	implementation(kotlin(Kotlin.reflect))
	implementation(kotlin(Kotlin.stdlibJdk8))
	implementation(Libraries.slf4j)
}

//tasks.bootJar {
//	enabled = false
////	archiveFileName.set("app.jar")
////	mainClass.set("com.avall.ms.attachments.MSAttachmentApplication")
//}

tasks.jar {
	enabled = true
//	archiveFileName.set("app.jar")
//	mainClass.set("com.avall.ms.attachments.MSAttachmentApplication")
}
