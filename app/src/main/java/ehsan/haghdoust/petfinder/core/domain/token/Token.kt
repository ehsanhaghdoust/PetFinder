package ehsan.haghdoust.petfinder.core.domain.token

data class Token(val tokenType: String,
                 val retrievedTime: Long,
                 val expiresIn: Int,
                 val accessToken: String)
