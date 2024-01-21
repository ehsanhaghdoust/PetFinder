package ehsan.haghdoust.petfinder.model.response

import com.google.gson.annotations.SerializedName

data class OAuthResponse(@SerializedName("token_type") val tokenType: String,
                         @SerializedName("expires_in") val expiresIn: Int,
                         @SerializedName("access_token") val accessToken: String)