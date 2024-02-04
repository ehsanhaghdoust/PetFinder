package ehsan.haghdoust.petfinder.view.activity.routing

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ehsan.haghdoust.petfinder.R
import ehsan.haghdoust.petfinder.model.general.NetworkError
import ehsan.haghdoust.petfinder.model.request.OAuthRequestModel
import ehsan.haghdoust.petfinder.model.response.OAuthResponse
import ehsan.haghdoust.petfinder.repository.database.AppDatabase
import ehsan.haghdoust.petfinder.repository.database.entity.UserCredential
import ehsan.haghdoust.petfinder.repository.network.ApiClient
import ehsan.haghdoust.petfinder.repository.network.ApiInterface
import ehsan.haghdoust.petfinder.repository.network.NetworkConstants
import ehsan.haghdoust.petfinder.util.NetworkCallState
import ehsan.haghdoust.petfinder.util.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StartViewModel(application: Application) : AndroidViewModel(application) {

    private val _networkCallState = MutableStateFlow(NetworkCallState.LOADING)
    val isLoading = _networkCallState.asStateFlow()
    private val _error = MutableLiveData<NetworkError>()
    val error: LiveData<NetworkError>
        get() = _error

    private val database = AppDatabase.getInstance(application.applicationContext).daoObject()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            checkCredentials()
        }
    }

    private suspend fun checkCredentials() {
        val userCredential = database.getToken()
        userCredential?.let {
            if ((userCredential.retrievedTime + userCredential.expiresIn) < System.currentTimeMillis()) {
                val oAuthRequestModel = OAuthRequestModel(grantType = NetworkConstants.Service.grantType,
                    clientId = NetworkConstants.Service.clientId,
                    clientSecret = NetworkConstants.Service.clientSecret)
            } else {
                getToken()
            }
        } ?: also {
            getToken()
        }
//        if (userCredential.isEmpty() || (userCredential.count() > 1)) {
//            database.deleteTokens()
//            getToken()
//        } else {
//            if ((userCredential[0].retrievedTime + userCredential[0].expiresIn) < System.currentTimeMillis()) {
//                val oAuthRequestModel = OAuthRequestModel(grantType = NetworkConstants.Service.grantType,
//                    clientId = NetworkConstants.Service.clientId,
//                    clientSecret = NetworkConstants.Service.clientSecret)
//            } else {
//                database.deleteTokens()
//                getToken()
//            }
//        }
    }

    private suspend fun getToken() {
        if(!NetworkUtil.isOnline(context = getApplication<Application>().applicationContext)) {
            _networkCallState.value = NetworkCallState.NoInternet
            return
        } else {
            val oAuthRequestModel = OAuthRequestModel(grantType = NetworkConstants.Service.grantType,
                clientId = NetworkConstants.Service.clientId,
                clientSecret = NetworkConstants.Service.clientSecret)
            val response = ApiClient().getClient().create(ApiInterface::class.java).getToken(oAuthRequestModel)
//            val response = ApiClient().getClient()..getToken(oAuthRequestModel)
            when {
                response.isSuccessful -> {
                    response.body()?.let {
                        _networkCallState.value = NetworkCallState.SUCCESS
                        saveToken(oAuthResponse = it)
                    } ?: also {
                        _networkCallState.value = NetworkCallState.FAIL
                        _error.value = NetworkError(0, R.string.error_data_corrupted)
                    }
                }
                else                  -> {
                    when(response.code()) {

                    }
                    _networkCallState.value = NetworkCallState.FAIL
                }
            }
        }
    }

    private suspend fun saveToken(oAuthResponse: OAuthResponse) {
        database.saveToken(userCredential = UserCredential(tokenType = oAuthResponse.tokenType,
            retrievedTime = System.currentTimeMillis(),
            expiresIn = oAuthResponse.expiresIn,
            accessToken = oAuthResponse.accessToken))
    }
}