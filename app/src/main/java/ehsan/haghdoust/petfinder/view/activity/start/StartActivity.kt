package ehsan.haghdoust.petfinder.view.activity.start

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import ehsan.haghdoust.petfinder.databinding.ActivityStartLayoutBinding
import ehsan.haghdoust.petfinder.util.NetworkCallState
import ehsan.haghdoust.petfinder.view.activity.BaseActivity
import ehsan.haghdoust.petfinder.view.activity.main.MainActivity
import kotlinx.coroutines.launch

class StartActivity : BaseActivity() {

    private val viewModel: StartViewModel by viewModels()
    private lateinit var binding: ActivityStartLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch {
            setObserver()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override suspend fun setObserver() {
        viewModel.loadingState.observe(this) { state ->

            when (state) {
                NetworkCallState.LOADING    -> {
                    print(state.name)
                    showLoader()
                }

                NetworkCallState.SUCCESS    -> {
                    print(state.name)
                    hideLoader()
                    startActivity(Intent(this@StartActivity, MainActivity::class.java))
                }

                NetworkCallState.FAIL       -> {
                    print(state.name)
                    viewModel.reloadToken()
                }

                NetworkCallState.NoInternet -> {
                    print(state.name)
                    hideLoader()
                }

                else -> {
                    print("null")
                }
            }
        }
    }
}