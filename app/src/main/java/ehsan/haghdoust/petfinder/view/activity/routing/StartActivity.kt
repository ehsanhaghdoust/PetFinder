package ehsan.haghdoust.petfinder.view.activity.routing

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import ehsan.haghdoust.petfinder.databinding.ActivityMainBinding
import ehsan.haghdoust.petfinder.databinding.ActivityStartLayoutBinding
import ehsan.haghdoust.petfinder.util.NetworkCallState
import ehsan.haghdoust.petfinder.view.activity.BaseActivity
import ehsan.haghdoust.petfinder.view.activity.main.MainActivity
import kotlinx.coroutines.launch

class StartActivity: BaseActivity() {

    private val viewModel: StartViewModel by viewModels()
    private lateinit var binding: ActivityStartLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setObserver()
//        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun setObserver() {
        lifecycleScope.launch {
            viewModel.isLoading.collect {
                when (it) {
                    NetworkCallState.LOADING -> {
                        print(it.name)
                        showLoader()
                    }

                    NetworkCallState.SUCCESS -> {
                        print(it.name)
                        hideLoader()
                    }

                    NetworkCallState.FAIL    -> {
                        print(it.name)
                        hideLoader()
                    }

                    NetworkCallState.NoInternet -> {
                        print(it.name)
                        hideLoader()
                    }
                }
            }
        }
    }
}