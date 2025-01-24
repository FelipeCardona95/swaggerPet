package org.example.store

import org.example.utils.BaseTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class StorePositiveTest : BaseTest() {
    private val storeService = StoreService()
    private val storeRequestBuilder = StoreRequestBuilder()

    @Test
    fun `fetch inventory status`() {
        val response = storeService.fetchInventory()

        assertEquals(200, response.statusCode())
        val inventory = response.jsonPath().getMap<String, Int>("")
        assert(inventory.isNotEmpty())
        println("Inventory: $inventory")
    }

    @Test
    fun `place a new order`() {
        val orderId = 1
        val orderBody = storeRequestBuilder.buildOrder(orderId)

        val response = storeService.placeOrder(orderBody)
        assertEquals(200, response.statusCode())
        val responseBody = response.jsonPath().getMap<String, Any>("")
        assertEquals(orderId, responseBody["id"])
        assertEquals("placed", responseBody["status"])
        println("Order Response: $responseBody")
    }

    @Test
    fun `fetch order by ID`() {
        val orderId = 3
        val response = storeService.fetchOrderById(orderId)
        assertEquals(200, response.statusCode())
        val responseBody = response.jsonPath().getMap<String, Any>("")
        assertEquals(orderId, responseBody["id"])
        println("Order Details: $responseBody")
    }

    @Test
    fun `delete an order`() {
        val orderId = 1
        val response = storeService.deleteOrder(orderId)
        assertEquals(200, response.statusCode())
        println("Order deleted successfully!")
    }
}
