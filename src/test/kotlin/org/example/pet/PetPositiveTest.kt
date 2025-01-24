package org.example.pet

import org.example.pet.factories.PetFactory
import org.example.utils.BaseTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PetPositiveTest : BaseTest() {

    private val petService = PetService()

    @Test
    fun `create a new pet with dynamic data`() {
        val newPet = PetFactory.createPet()

        val response = petService.createPet(newPet)

        assertEquals(200, response.statusCode())
        assertEquals(newPet.name, response.jsonPath().getString("name"))
        assertEquals(newPet.status, response.jsonPath().getString("status"))
    }

    @Test
    fun `update an existing pet`() {
        val existingPet = PetFactory.createPet()
        petService.createPet(existingPet)

        val updatedPet = PetFactory.updatePet(existingPet, "UpdatedName", "sold")

        val response = petService.updatePet(updatedPet)

        assertEquals(200, response.statusCode())
        assertEquals("UpdatedName", response.jsonPath().getString("name"))
        assertEquals("sold", response.jsonPath().getString("status"))
    }

    @Test
    fun `get pet by ID`() {
        val newPet = PetFactory.createPet()
        petService.createPet(newPet)

        val response = petService.getPetById(newPet.id!!)

        assertEquals(200, response.statusCode())
        assertEquals(newPet.name, response.jsonPath().getString("name"))
    }
}
