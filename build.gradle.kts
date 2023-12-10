plugins {
    kotlin("jvm") version "1.9.21"
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0-RC")
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks {
    wrapper {
        gradleVersion = "8.5"
    }
}


