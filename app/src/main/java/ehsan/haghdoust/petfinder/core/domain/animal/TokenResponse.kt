package ehsan.haghdoust.petfinder.core.domain.animal

data class TokenResponse(val token_type: String,
                         val expires_in: Int,
                         val access_token: String)