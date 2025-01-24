package org.example.pet

data class PetPojo(
    var id: Int? = null,
    var name: String? = null,
    var category: Category? = null,
    var photoUrls: List<String> = listOf(),
    var tags: List<Tag> = listOf(),
    var status: String? = null
)

data class Category(
    var id: Int? = null,
    var name: String? = null
)

data class Tag(
    var id: Int? = null,
    var name: String? = null
)
