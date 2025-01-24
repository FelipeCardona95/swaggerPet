package org.example.user

import org.example.utils.BaseTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UserPositiveTest : BaseTest() {
    private val userService = UserService()
    private val userRequestBuilder = UserRequestBuilder()

    @Test
    fun `fetch user details by username`() {
        val username = "testuser"
        val response = userService.fetchUserDetails(username)

        assertEquals(200, response.statusCode())
        val user = response.jsonPath().getMap<String, Any>("")
        assertEquals(username, user["username"])
        println("User Details: $user")
    }

    @Test
    fun `create a new user`() {
        val username = "testuser"
        val userBody = userRequestBuilder.buildUser(username)

        val response = userService.createUser(userBody)
        assertEquals(200, response.statusCode())
        println("User Created Successfully!")
    }

    @Test
    fun `log in user`() {
        val response = userService.loginUser("testuser", "password123")
        assertEquals(200, response.statusCode())
        println("Login Response: ${response.body.asString()}")
    }

    @Test
    fun `log out user`() {
        val response = userService.logoutUser()
        assertEquals(200, response.statusCode())
        println("User logged out successfully!")
    }
}
