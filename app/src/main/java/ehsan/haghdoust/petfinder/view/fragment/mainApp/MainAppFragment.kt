package ehsan.haghdoust.petfinder.view.fragment.mainApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ehsan.haghdoust.petfinder.databinding.FragmentMainAppBinding

class MainAppFragment : Fragment() {

    private lateinit var binding: FragmentMainAppBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainAppBinding.inflate(inflater, container, false)

        return binding.root
    }
}