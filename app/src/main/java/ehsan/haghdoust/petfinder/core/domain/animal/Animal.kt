package ehsan.haghdoust.petfinder.core.domain.animal

data class Animal(val id: Long,
                  val organization_id: String?,
                  val url: String?,
                  val type: String?,
                  val species: String?,
                  val breeds: Breeds?,
                  val colors: Colors?,
                  val age: String?,
                  val gender: String?,
                  val size: String?,
                  val coat: String?,
                  val attributes: Attributes?,
                  val environment: Environment?,
                  val tags: List<String>?,
                  val name: String?,
                  val description: String?,
                  val organization_animal_id: String?,
                  val photos: List<Photos>?,
                  val primary_photo_cropped: List<Photos>?,
                  val videos: List<Videos>?,
                  val status: String?,
                  val status_changed_at: String?,
                  val published_at: String?,
                  val distance: Float?,
                  val contact: Contact?,
                  val _links: String?)