plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
    application
}

group = "dev.twelveoclock"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {

    implementation(platform(kotlin("bom")))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

    implementation("org.eclipse.jgit:org.eclipse.jgit:6.1.0.202203080745-r")

}

application {
    mainClassName = "dev.twelveoclock.blackhole.Main"
}