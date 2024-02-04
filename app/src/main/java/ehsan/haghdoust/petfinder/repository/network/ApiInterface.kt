package ehsan.haghdoust.petfinder.repository.network

import ehsan.haghdoust.petfinder.model.request.OAuthRequestModel
import ehsan.haghdoust.petfinder.model.response.OAuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @POST(Constants.endpoints.token)
    suspend fun getToken(@Body oAuthRequestModel: OAuthRequestModel): Response<OAuthResponse>

    @GET("/animals")
    suspend fun animals()

    @GET("/animal")
    suspend fun animal()
}