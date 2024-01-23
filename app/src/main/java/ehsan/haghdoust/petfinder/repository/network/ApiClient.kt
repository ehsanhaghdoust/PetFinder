package ehsan.haghdoust.petfinder.repository.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    private lateinit var retrofit: Retrofit
    val client = ApiClient().getClient().create(ApiInterface::class.java)

    private fun getClient(): Retrofit {
        if (!this::retrofit.isInitialized) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()
            val client = OkHttpClient
                .Builder()
                .addInterceptor(interceptor).build()

            retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.BASEURL + Constants.routingVersion)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        return retrofit
    }
}