package ehsan.haghdoust.petfinder.core.data.animal

import ehsan.haghdoust.petfinder.core.domain.animal.Animal

interface AnimalDataSource {
    suspend fun getAnimals(): List<Animal>?

    suspend fun getAnimal(id: Long): Animal
}