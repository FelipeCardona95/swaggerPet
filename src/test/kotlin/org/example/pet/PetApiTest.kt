package org.example.pet

import io.restassured.RestAssured
import org.example.utils.BaseTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class PetApiTest : BaseTest() {

    @Test
    fun `get pet by id`() {
        val response = RestAssured
            .given()
            .pathParam("petId", 1)
            .`when`()
            .get("/pet/{petId}")

        assertEquals(200, response.statusCode())
    }
}
