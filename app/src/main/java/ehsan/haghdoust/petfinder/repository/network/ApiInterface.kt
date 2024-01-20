package ehsan.haghdoust.petfinder.repository.network

import retrofit2.http.GET

interface ApiInterface {

    @GET("/animals")
    fun animals()

    @GET("/animal")
    fun animal()
}