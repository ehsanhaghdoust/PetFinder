package ehsan.haghdoust.petfinder.view.activity

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import ehsan.haghdoust.petfinder.view.dialog.LoadingIndicator

open class BaseActivity: AppCompatActivity() {

    var loadingIndicator = LoadingIndicator()

    fun showLoader() {
        loadingIndicator.show(supportFragmentManager, "LoadingIndicator")
    }

    fun hideLoader() {
        loadingIndicator.dismissNow()
    }

    open suspend fun setObserver() {}
}