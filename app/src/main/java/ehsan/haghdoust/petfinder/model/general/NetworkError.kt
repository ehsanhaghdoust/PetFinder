package ehsan.haghdoust.petfinder.model.general

import androidx.annotation.StringRes


data class NetworkError(var errorCode: Int? = null, @StringRes var message: Int? = null){

    // Override toString() method to provide custom error message
    override fun toString(): String {
        return "errorCode: $errorCode, CustomError: $message, }"
    }
}