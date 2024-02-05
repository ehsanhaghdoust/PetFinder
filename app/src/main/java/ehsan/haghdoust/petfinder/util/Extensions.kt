package ehsan.haghdoust.petfinder.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

//fun AppCompatActivity.showMessage() {
//    val snackbar = Snackbar.make(this.root , this, "", Snackbar.LENGTH_SHORT)
//}

fun View.showSnack(message: String, duration: Int = Snackbar.LENGTH_LONG, actionText: String? = null, action: (() -> Unit)? = null) {
    Snackbar.make(this, message, duration).setAction(actionText) { action?.invoke() }.show()
}