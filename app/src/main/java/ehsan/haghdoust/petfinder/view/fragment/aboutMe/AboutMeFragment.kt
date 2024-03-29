package ehsan.haghdoust.petfinder.view.fragment.aboutMe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ehsan.haghdoust.petfinder.databinding.FragmentAboutMeBinding

class AboutMeFragment : Fragment() {

    //    private lateinit var viewModel: AboutMeViewModel
    private lateinit var binding: FragmentAboutMeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutMeBinding.inflate(layoutInflater, container, false)

        return binding.root
    }
}