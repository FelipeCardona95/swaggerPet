package org.example.pet

import io.restassured.RestAssured
import org.example.pet.factories.PetFactory
import org.example.utils.BaseTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PetIntegrationTest : BaseTest() {

    private val petService = PetService()

    @Test
    fun `create, update, and delete a pet successfully`() {
        // Step 1: Create a pet
        val newPet = PetFactory.createPet()
        val createResponse = petService.createPet(newPet)
        assertEquals(200, createResponse.statusCode())
        assertEquals(newPet.name, createResponse.jsonPath().getString("name"))

        // Step 2: Update the pet
        val updatedPet = PetFactory.updatePet(
            existingPet = newPet,
            newName = "Updated_${newPet.name}",
            newStatus = "sold"
        )
        val updateResponse = petService.updatePet(updatedPet)
        assertEquals(200, updateResponse.statusCode())
        assertEquals(updatedPet.name, updateResponse.jsonPath().getString("name"))
        assertEquals("sold", updateResponse.jsonPath().getString("status"))

        // Step 3: Verify the update
        val getResponse = petService.getPetById(newPet.id!!)
        assertEquals(200, getResponse.statusCode())
        assertEquals(updatedPet.name, getResponse.jsonPath().getString("name"))
        assertEquals("sold", getResponse.jsonPath().getString("status"))

        // Step 4: Delete the pet
        val deleteResponse = RestAssured
            .given()
            .pathParam("petId", newPet.id!!)
            .`when`()
            .delete("/pet/{petId}")
        assertEquals(200, deleteResponse.statusCode())

        // Step 5: Verify the pet no longer exists
        val getAfterDeleteResponse = petService.getPetById(newPet.id!!)
        assertEquals(404, getAfterDeleteResponse.statusCode())
    }
}
