package ehsan.haghdoust.core.data.animal

class AnimalRepository(private val animalDataSource: AnimalDataSource) {
    suspend fun getAnimals() = animalDataSource.getAnimals()

    suspend fun getAnimal(id: Long) = animalDataSource.getAnimal(id = id)
}