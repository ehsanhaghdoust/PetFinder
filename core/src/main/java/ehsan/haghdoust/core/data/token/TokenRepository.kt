package ehsan.haghdoust.core.data.token

import ehsan.haghdoust.core.domain.token.Token
import ehsan.haghdoust.core.domain.animal.TokenRequest

class TokenRepository(private val tokenDataSource: TokenDataSource) {
    suspend fun getToken(tokenRequest: TokenRequest) = tokenDataSource.getToken(tokenRequest = tokenRequest)

    suspend fun saveToken(token: Token) = tokenDataSource.saveToken(token = token)
}