package ehsan.haghdoust.petfinder.view.activity.start

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
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class StartViewModel(application: Application) : AndroidViewModel(application) {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    private val _networkCallState = MutableLiveData(NetworkCallState.LOADING)
    val loadingState = _networkCallState
    private val _error = MutableLiveData<NetworkError>()
    val error: LiveData<NetworkError>
        get() = _error

    private val database = AppDatabase.getInstance(application.applicationContext).daoObject()

    init {
        viewModelScope.launch {
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
    }

    fun reloadToken() {
        viewModelScope.launch {
            getToken()
        }

    }

    private suspend fun getToken() {
        if (!NetworkUtil.isOnline(context = getApplication<Application>().applicationContext)) {
            _networkCallState.value = NetworkCallState.NoInternet
            return
        } else {
            val oAuthRequestModel = OAuthRequestModel(grantType = NetworkConstants.Service.grantType,
                clientId = NetworkConstants.Service.clientId,
                clientSecret = NetworkConstants.Service.clientSecret)
            val response = ApiClient().getClient().create(ApiInterface::class.java)
                .getToken(oAuthRequestModel) //            val response = ApiClient().getClient()..getToken(oAuthRequestModel)
            when {
                response.isSuccessful -> {
                    response.body()?.let {
                        _networkCallState.value = NetworkCallState.SUCCESS //                        saveToken(oAuthResponse = it)
                    } ?: also {
                        _networkCallState.value = NetworkCallState.FAIL
                        _error.value = NetworkError(0, R.string.error_data_corrupted)
                    }
                }

                else                  -> {
                    _networkCallState.postValue(NetworkCallState.FAIL)
                    when (response.code()) {
                    }
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