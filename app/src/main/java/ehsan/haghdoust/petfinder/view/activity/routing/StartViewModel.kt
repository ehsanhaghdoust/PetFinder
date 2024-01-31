package ehsan.haghdoust.petfinder.view.activity.routing

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ehsan.haghdoust.petfinder.model.request.OAuthRequestModel
import ehsan.haghdoust.petfinder.model.response.OAuthResponse
import ehsan.haghdoust.petfinder.repository.database.AppDatabase
import ehsan.haghdoust.petfinder.repository.database.entity.UserCredential
import ehsan.haghdoust.petfinder.repository.network.ApiClient
import ehsan.haghdoust.petfinder.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StartViewModel(application: Application) : AndroidViewModel(application) {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val database = AppDatabase.getInstance(application.applicationContext).daoObject()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            checkCredentials()
        }
    }

    private suspend fun checkCredentials() {
        val userCredential = database.getToken()
        if (userCredential.isEmpty()) {
            getToken()
        } else if (userCredential.count() > 1) {
            database.deleteTokens()
            getToken()
        } else {
            if ((userCredential[0].retrievedTime + userCredential[0].expiresIn) < System.currentTimeMillis()) {
                val oAuthRequestModel = OAuthRequestModel(grantType = Constants.Service.grantType,
                    clientId = Constants.Service.clientId,
                    clientSecret = Constants.Service.clientSecret)
                val token = ApiClient().client.getToken(oAuthRequestModel)
            }
        }
//        _isLoading.value = userCredential != null
    }

    private suspend fun getToken() {
        val oAuthRequestModel = OAuthRequestModel(grantType = Constants.Service.grantType,
            clientId = Constants.Service.clientId,
            clientSecret = Constants.Service.clientSecret)

        val response = ApiClient().client.getToken(oAuthRequestModel)
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