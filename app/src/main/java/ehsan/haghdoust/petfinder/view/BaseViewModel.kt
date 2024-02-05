package ehsan.haghdoust.petfinder.view

import kotlinx.coroutines.CoroutineExceptionHandler

class BaseViewModel {
    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }
}