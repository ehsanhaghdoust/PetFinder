package ehsan.haghdoust.petfinder.repository.network

import ehsan.haghdoust.petfinder.model.request.OAuthRequestModel
import ehsan.haghdoust.petfinder.model.response.OAuthResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET(Constants.endpoints.token)
    suspend fun token(oAuthRequestModel: OAuthRequestModel): Response<OAuthResponse>

    @GET("/animals")
    suspend fun animals()

    @GET("/animal")
    suspend fun animal()
}