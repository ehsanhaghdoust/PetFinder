package ehsan.haghdoust.petfinder.view.activity.routing

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import ehsan.haghdoust.petfinder.view.activity.MainActivity

class RoutingActivity: AppCompatActivity() {

    private val viewModel: RoutingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition {
            viewModel.isLoading.value
        }
        startActivity(Intent(this, MainActivity::class.java))
    }
}