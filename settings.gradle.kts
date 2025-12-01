rootProject.name = "minecraft-safe-resources"
include(
    "plugin",
    "example",
)

pluginManagement {
    repositories {
        maven { url = uri("./plugin/build/local-repo") }
        gradlePluginPortal()
    }
}
