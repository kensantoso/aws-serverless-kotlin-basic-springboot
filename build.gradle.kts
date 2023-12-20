import org.jetbrains.kotlin.gradle.tasks.KotlinCompile // For `KotlinCompile` task below

plugins {
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.2"
    kotlin("jvm") version "1.9.20"
    kotlin("plugin.spring") version "1.9.0" // The Kotlin Spring plugin
}

group = "com.example"
version = "0.0.1-SNAPSHOT"


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.0")
//    {
//        exclude(group = "org.springframework.boot", module="spring-boot-starter-tomcat")
//    }
    implementation("com.amazonaws.serverless:aws-serverless-java-container-springboot3:[2.0-SNAPSHOT,)")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin") // Jackson extensions for Kotlin for working with JSON
    implementation("org.jetbrains.kotlin:kotlin-reflect") // Kotlin reflection library, required for working with Spring
    runtimeOnly("com.h2database:h2")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")

}

java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}
tasks.withType<KotlinCompile> { // Settings for `KotlinCompile` tasks
    kotlinOptions { // Kotlin compiler options
        freeCompilerArgs = listOf("-Xjsr305=strict") // `-Xjsr305=strict` enables the strict mode for JSR-305 annotations
        jvmTarget = "17" // This option specifies the target version of the generated JVM bytecode

    }
}

tasks.wrapper{
    gradleVersion = "8.5"
}