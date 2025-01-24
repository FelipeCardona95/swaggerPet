package org.example.user

import io.restassured.RestAssured
import io.restassured.response.Response
import io.restassured.http.ContentType

class UserService {
    fun fetchUserDetails(username: String): Response {
        return RestAssured
            .given()
            .pathParam("username", username)
            .`when`()
            .get("/user/{username}")
    }

    fun createUser(userBody: Map<String, Any>): Response {
        return RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(userBody)
            .`when`()
            .post("/user")
    }

    fun loginUser(username: String, password: String): Response {
        return RestAssured
            .given()
            .queryParam("username", username)
            .queryParam("password", password)
            .`when`()
            .get("/user/login")
    }

    fun logoutUser(): Response {
        return RestAssured
            .given()
            .`when`()
            .get("/user/logout")
    }
}
