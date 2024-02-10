package ehsan.haghdoust.petfinder.view.base

import kotlinx.coroutines.CoroutineExceptionHandler

class BaseViewModel {
    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }
}