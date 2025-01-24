package org.example.pet

import io.restassured.RestAssured
import org.example.pet.factories.PetFactory
import org.example.utils.BaseTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PetNegativeTest : BaseTest() {

    private val petService = PetService()

    @Test
    fun `get non-existent pet`() {
        val invalidPetId = 999999

        val response = petService.getPetById(invalidPetId)

        // Assert the status code
        assertEquals(404, response.statusCode())
        // Log and validate the response body
        val responseBody = response.body.asString()
        assert(responseBody.contains("Pet not found") || responseBody.isEmpty())
    }

    @Test
    fun `create pet with invalid data`() {
        // Create an invalid pet object
        val invalidPet = PetPojo(
            id = null, // Missing ID
            name = "aaa", // Empty name
            category = null, // Null category
            photoUrls = emptyList(), // No photo URLs
            tags = emptyList(), // No tags
            status = "unknown_status" // Invalid status
        )

        val response = petService.createPet(invalidPet)

        // Assert the status code
        assertEquals(500, response.statusCode())

        // Log and validate the response body
        val responseBody = response.body.asString()
    }

    @Test
    fun `update non-existent pet`() {
        // Create a pet object with a non-existent ID
        val nonExistentPet = PetFactory.createPet(id = 999999)

        val response = petService.updatePet(nonExistentPet)
        // Assert the status code
        assertEquals(404, response.statusCode())

        // Log and validate the response body
        val responseBody = response.body.asString()
        assert(responseBody.contains("Pet not found") || responseBody.isEmpty())
    }

    @Test
    fun `update pet by form data with invalid ID`() {
        val invalidPet = PetPojo(
            id = 99999, // Missing ID
            name = "aaa", // Empty name
            category = null, // Null category
            photoUrls = emptyList(), // No photo URLs
            tags = emptyList(), // No tags
            status = "unknown_status" // Invalid status
        )

        val response = petService.updatePet(invalidPet)

        // Assert the status code
        assertEquals(404, response.statusCode())

        // Log and validate the response body
        val responseBody = response.body.asString()
        assert(responseBody.contains("Pet not found") || responseBody.isEmpty())
    }

    @Test
    fun `delete non-existent pet`() {
        val invalidPetId = 999999

        val response = RestAssured
            .given()
            .pathParam("petId", invalidPetId)
            .`when`()
            .delete("/pet/{petId}")

        // Assert the status code
        assertEquals(200, response.statusCode())

        // Log and validate the response body
        val responseBody = response.body.asString()
        assert(responseBody.contains("Pet deleted") || responseBody.isEmpty())
    }
}
