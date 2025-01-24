package org.example.store

import io.restassured.RestAssured
import io.restassured.response.Response
import io.restassured.http.ContentType

class StoreService {
    fun fetchInventory(): Response {
        return RestAssured
            .given()
            .`when`()
            .get("/store/inventory")
    }

    fun placeOrder(orderBody: Map<String, Any>): Response {
        return RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(orderBody)
            .`when`()
            .post("/store/order")
    }

    fun fetchOrderById(orderId: Int): Response {
        return RestAssured
            .given()
            .pathParam("orderId", orderId)
            .`when`()
            .get("/store/order/{orderId}")
    }

    fun deleteOrder(orderId: Int): Response {
        return RestAssured
            .given()
            .pathParam("orderId", orderId)
            .`when`()
            .delete("/store/order/{orderId}")
    }
}
