plugins {
    kotlin("jvm") version "1.9.10" // Versión más reciente del plugin de Kotlin
}

repositories {
    mavenCentral() // Repositorio para las dependencias
}

dependencies {
    // Kotlin test framework
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.9.10")

    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")

    // RestAssured
    testImplementation("io.rest-assured:kotlin-extensions:5.3.0")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")
    implementation("io.rest-assured:rest-assured:5.3.0")

}

tasks.test {
    useJUnitPlatform() // Usar la plataforma de pruebas JUnit
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(20)) // Usar Java 20
    }
}
