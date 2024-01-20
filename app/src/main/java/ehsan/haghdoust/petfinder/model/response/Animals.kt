package ehsan.haghdoust.petfinder.model.response

import com.google.gson.annotations.SerializedName

data class Animals(@SerializedName("id") val id: Int,
                   @SerializedName("organization_id") val organizationId: String,
                   @SerializedName("url") val url: String,
                   @SerializedName("type") val type: String,
                   @SerializedName("species") val species: String,
                   @SerializedName("breeds") val breeds: Breeds,
                   @SerializedName("colors") val colors: Colors)

data class Breeds(@SerializedName("primary") val primary: String,
                  @SerializedName("secondary") val secondary: String?,
                  @SerializedName("mixed") val mixed: Boolean,
                  @SerializedName("unknown") val unknown: Boolean)

data class Colors(@SerializedName("primary") val primary: String,
                  @SerializedName("secondary") val secondary: String?,
                  @SerializedName("tertiary") val tertiary: String?)