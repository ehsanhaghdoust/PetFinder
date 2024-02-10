package ehsan.haghdoust.core.data.animal

import ehsan.haghdoust.core.domain.animal.Animal

interface AnimalDataSource {
    suspend fun getAnimals(): List<Animal>?

    suspend fun getAnimal(id: Long): Animal
}