package org.example.store

class StoreRequestBuilder {
    fun buildOrder(orderId: Int): Map<String, Any> {
        return mapOf(
            "id" to orderId,
            "petId" to 12345,
            "quantity" to 2,
            "shipDate" to "2025-01-24T10:10:10.000Z",
            "status" to "placed",
            "complete" to true
        )
    }
}
