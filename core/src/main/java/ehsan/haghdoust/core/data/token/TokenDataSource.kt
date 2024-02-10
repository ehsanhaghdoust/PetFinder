package ehsan.haghdoust.core.data.token

import ehsan.haghdoust.core.domain.token.Token
import ehsan.haghdoust.core.domain.animal.TokenRequest
import ehsan.haghdoust.core.domain.animal.TokenResponse

interface TokenDataSource {
    suspend fun getToken(tokenRequest: TokenRequest): TokenResponse

    suspend fun saveToken(token: Token)
}