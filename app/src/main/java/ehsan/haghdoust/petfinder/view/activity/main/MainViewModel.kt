package ehsan.haghdoust.petfinder.view.activity.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ehsan.haghdoust.petfinder.model.request.OAuthRequestModel
import ehsan.haghdoust.petfinder.model.response.OAuthResponse
import ehsan.haghdoust.petfinder.repository.database.AppDatabase
import ehsan.haghdoust.petfinder.repository.database.entity.UserCredential
import ehsan.haghdoust.petfinder.repository.network.ApiClient
import ehsan.haghdoust.petfinder.repository.network.ApiInterface
import ehsan.haghdoust.petfinder.repository.network.NetworkConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val response = MutableLiveData<Result<OAuthResponse>>().apply {
        this.value = null

    }
    init {
        viewModelScope.launch(Dispatchers.IO) {
            getToken()
        }
    }

    private suspend fun getToken() {
        val oAuthRequestModel = OAuthRequestModel(grantType = NetworkConstants.Service.grantType,
            clientId = NetworkConstants.Service.clientId,
            clientSecret = NetworkConstants.Service.clientSecret)

        val response = ApiClient().getClient().create(ApiInterface::class.java).getToken(oAuthRequestModel)
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