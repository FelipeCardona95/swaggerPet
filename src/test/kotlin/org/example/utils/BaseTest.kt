package org.example.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.restassured.RestAssured
import io.restassured.config.ObjectMapperConfig
import io.restassured.config.RestAssuredConfig
import org.junit.jupiter.api.BeforeAll

open class BaseTest {

    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() {
            // Register the Kotlin module with Jackson
            val objectMapper = ObjectMapper().registerKotlinModule()

            // Configure RestAssured to use Jackson with the Kotlin module
            RestAssured.config = RestAssuredConfig.config().objectMapperConfig(
                ObjectMapperConfig.objectMapperConfig().jackson2ObjectMapperFactory { _, _ -> objectMapper }
            )

            // Set the base URI for the API
            RestAssured.baseURI = "http://localhost:8080/api/v3" // Update with your API's base URL
        }
    }
}
