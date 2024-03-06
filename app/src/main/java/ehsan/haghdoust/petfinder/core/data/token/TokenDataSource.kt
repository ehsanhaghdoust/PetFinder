package ehsan.haghdoust.petfinder.core.data.token

import ehsan.haghdoust.petfinder.core.domain.token.Token
import ehsan.haghdoust.petfinder.core.domain.animal.TokenRequest
import ehsan.haghdoust.petfinder.core.domain.animal.TokenResponse

interface TokenDataSource {
    suspend fun getToken(tokenRequest: TokenRequest): TokenResponse

    suspend fun saveToken(token: Token)
}