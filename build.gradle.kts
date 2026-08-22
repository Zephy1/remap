import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    kotlin("jvm") version "2.4.10"
    `maven-publish`
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(25))

group = "com.github.replaymod"
version = "SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.spongepowered.org/repository/maven-public/")
}

dependencies {
    api("org.jetbrains.kotlin:kotlin-compiler-embeddable:2.4.10")
    implementation(kotlin("stdlib"))
    implementation("org.ow2.asm:asm-tree:9.10.1")
    api("org.cadixdev:lorenz:0.5.8")
    testRuntimeOnly("org.spongepowered:mixin:0.8.7")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("remap")
}

publishing {
    publications {
        create("maven", MavenPublication::class) {
            from(components["java"])
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.compileKotlin {
    compilerOptions {
        apiVersion.set(KotlinVersion.KOTLIN_2_4)
        languageVersion.set(KotlinVersion.KOTLIN_2_4)
    }
}
