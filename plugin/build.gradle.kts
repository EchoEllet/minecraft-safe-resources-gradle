plugins {
    `kotlin-dsl`
    embeddedKotlin("jvm")
    `java-gradle-plugin`
    `maven-publish`
    id("com.gradle.plugin-publish") version "2.0.0"
}

group = "dev.echoellet"
version = "1.0.0"
description =
    "A Gradle plugin to generate Java/Kotlin object constants to reference Minecraft resources or assets, such as en_us.json in code in a type-safe way without any hardcoding"

repositories {
    mavenCentral()
}

dependencies {
    implementation(gradleApi())
    implementation("com.google.code.gson:gson:2.13.2")
}

gradlePlugin {
    val gitHubRepo = "https://github.com/EchoEllet/minecraft-safe-resources-gradle"
    website = gitHubRepo
    vcsUrl = gitHubRepo

    plugins.create("minecraftSafeResourcesPlugin") {
        id = "dev.echoellet.minecraft-safe-resources"
        implementationClass = "dev.echoellet.minecraft_safe_resources.MinecraftSafeResourcesPlugin"
        displayName = "Minecraft Safe Resources"
        description = project.description
        version = project.version
        tags = listOf("minecraft", "resources", "generator", "json")
    }
}

java {
    withSourcesJar()
}

tasks.jar {
    manifest {
        attributes(mapOf("Implementation-Version" to version))
    }
}

// For testing purposes https://docs.gradle.org/current/userguide/preparing_to_publish.html#local_publishing_and_testing
publishing {
    repositories {
        maven { url = uri(layout.buildDirectory.dir("local-repo")) }
    }
}
