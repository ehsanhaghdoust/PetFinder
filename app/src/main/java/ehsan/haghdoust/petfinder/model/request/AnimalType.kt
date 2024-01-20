package ehsan.haghdoust.petfinder.model.request

import com.google.gson.annotations.SerializedName

data class AnimalType(@SerializedName("name") val name: String,
                      @SerializedName("coats") val coats: List<String>,
                      @SerializedName("colors") val colors: List<String>,
                      @SerializedName("_links") val links: Links)

data class Links(@SerializedName("self") val self: RelativeUrl,
                 @SerializedName("breeds") val breeds: RelativeUrl)

data class RelativeUrl(@SerializedName("href") val href: String)