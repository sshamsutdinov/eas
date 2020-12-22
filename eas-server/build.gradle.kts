plugins {
    java
    kotlin("jvm")
    id("org.springframework.boot")
}

dependencies {
    //kotlin
    implementation(kotlin("stdlib"))
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")

    //logging
    implementation("org.apache.logging.log4j:log4j-core:2.12.0")
    implementation("org.apache.logging.log4j:log4j-api-kotlin:1.0.0")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.12.0")

    //spring
    implementation("org.springframework.boot:spring-boot-starter-web:2.3.4.RELEASE") {
        exclude("org.springframework.boot", "spring-boot-starter-logging")
    }
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb:2.3.4.RELEASE") {
        exclude("org.springframework.boot", "spring-boot-starter-logging")
    }

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.8")

    //test
    testImplementation("io.mockk:mockk:1.9.3")
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.4.2")
    /*testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.0")*/
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.3.4.RELEASE") {
        exclude("org.springframework.boot", "spring-boot-starter-logging")
    }
}
