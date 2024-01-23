package ehsan.haghdoust.petfinder.view.activity.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ehsan.haghdoust.petfinder.model.request.OAuthRequestModel
import ehsan.haghdoust.petfinder.model.response.OAuthResponse
import ehsan.haghdoust.petfinder.repository.database.AppDatabase
import ehsan.haghdoust.petfinder.repository.database.entity.UserCredential
import ehsan.haghdoust.petfinder.repository.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getToken()
        }
    }

    private suspend fun getToken() {
        val oAuthRequestModel = OAuthRequestModel(grantType = "client_credentials",
            clientId = "vvMWwkQiDu5GTC7afNvLg9AROWGUShSGmBR4tjBodCSpJ2kqGb",
            clientSecret = "sVHaFGdCPkDrxR8bJJ4ToKqanqn9GYObXv224tf4")

        val response = ApiClient().client.token(oAuthRequestModel)
        when {
            response.isSuccessful -> {
                response.body()?.let {
                    saveToken(oAuthResponse = it)
                } ?: also {

                }
            }

            else                  -> {

            }
        }
    }

    private suspend fun saveToken(oAuthResponse: OAuthResponse) {
        AppDatabase.getInstance(context = getApplication<Application>().applicationContext).daoObject().saveToken(userCredential = UserCredential(
                tokenType = oAuthResponse.tokenType,
                retrievedTime = System.currentTimeMillis(),
                expiresIn = oAuthResponse.expiresIn,
                accessToken = oAuthResponse.accessToken))
    }

}