package org.example.user

class UserRequestBuilder {
    fun buildUser(username: String): Map<String, Any> {
        return mapOf(
            "id" to 1,
            "username" to username,
            "firstName" to "Test",
            "lastName" to "User",
            "email" to "testuser@example.com",
            "password" to "password123",
            "phone" to "1234567890",
            "userStatus" to 1
        )
    }
}
