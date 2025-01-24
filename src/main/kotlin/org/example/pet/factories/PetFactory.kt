package org.example.pet.factories

import org.example.pet.Category
import org.example.pet.PetPojo
import org.example.pet.Tag
import kotlin.random.Random

object PetFactory {

    fun createPet(
        id: Int = Random.nextInt(1000, 9999),
        name: String = "Pet_${Random.nextInt(1000)}",
        status: String = "available"
    ): PetPojo {
        return PetPojo(
            id = id,
            name = name,
            category = Category(id = 1, name = "Dogs"),
            photoUrls = listOf("http://example.com/photo"),
            tags = listOf(Tag(id = 0, name = "Tag_${Random.nextInt(100)}")),
            status = status
        )
    }

    fun updatePet(existingPet: PetPojo, newName: String, newStatus: String): PetPojo {
        return existingPet.copy(
            name = newName,
            status = newStatus
        )
    }
}
