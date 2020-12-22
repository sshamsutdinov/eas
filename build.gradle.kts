import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.api.tasks.testing.logging.TestExceptionFormat

buildscript {
    repositories {
        mavenCentral()
        mavenLocal()
    }
}

plugins {
    kotlin("jvm") version "1.3.72" apply false
    java
    id("org.springframework.boot") version "2.3.4.RELEASE" apply false
}

subprojects {
    group = "ru.eas"

    repositories {
        mavenCentral()
        mavenLocal()
    }

    tasks {
        withType<JavaCompile> {
            options.compilerArgs.add("-Xlint:all")
        }

        withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_11.toString()
                allWarningsAsErrors = true
                freeCompilerArgs = listOf("-Xjvm-default=enable")
            }
        }

        withType<Jar> {
            archiveFileName.set("${project.name}.jar")
        }

        withType<Test> {
            useJUnitPlatform()

            jvmArgs = listOf("-Xmx2048m")
            testLogging {
                events(
                    TestLogEvent.PASSED,
                    TestLogEvent.SKIPPED,
                    TestLogEvent.FAILED
                )
                showStandardStreams = true
                exceptionFormat = TestExceptionFormat.FULL
            }
        }
    }
}


