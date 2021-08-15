plugins {
    `kotlin-dsl`
}

repositories {
    maven {
        name = "WefoxNexus"
        url = uri("https://registry.wefox.io/repository/maven/")
        credentials {
            username = System.getenv("NEXUS_USERNAME")
            password = System.getenv("NEXUS_PASSWORD")
        }
    }
}