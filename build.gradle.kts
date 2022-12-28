plugins {
    kotlin("jvm") version "1.8.0"
    kotlin("plugin.serialization") version "1.7.10"
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
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")

    implementation("org.eclipse.jgit:org.eclipse.jgit:6.2.0.202206071550-r")

}

application {
    mainClassName = "dev.twelveoclock.blackhole.Main"
}