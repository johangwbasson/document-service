plugins {
    kotlin("jvm") version "2.2.20"
}

group = "net.johanbasson"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.http4k.core)
    implementation(libs.http4k.server.netty)
    implementation(libs.http4k.format.kotlinx.serialization)
    implementation(libs.http4k.format.jackson)
    implementation(libs.http4k.client.okhttp)
    implementation(libs.http4k.api.openapi)
    implementation(libs.http4k.api.ui.swagger)

    implementation(libs.http4k.ops.micrometer)
    implementation(libs.http4k.ops.opentelemetry)
    implementation(libs.http4k.ops.resilience4j)


    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}