package org.example.pet

import io.restassured.RestAssured
import io.restassured.response.Response


class PetService {

    fun createPet(pet: PetPojo): Response {
        return RestAssured
            .given()
            .contentType("application/json")
            .body(pet)
            .`when`()
            .post("/pet")
    }

    fun getPetById(petId: Int): Response {
        return RestAssured
            .given()
            .pathParam("petId", petId)
            .`when`()
            .get("/pet/{petId}")
    }

    fun updatePet(pet: PetPojo): Response {
        return RestAssured
            .given()
            .contentType("application/json")
            .body(pet)
            .`when`()
            .put("/pet")
    }
}
