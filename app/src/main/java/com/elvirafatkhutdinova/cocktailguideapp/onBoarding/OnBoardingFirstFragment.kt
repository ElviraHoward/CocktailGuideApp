package com.elvirafatkhutdinova.cocktailguideapp.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.elvirafatkhutdinova.cocktailguideapp.R
import com.elvirafatkhutdinova.cocktailguideapp.databinding.FragmentOnBoardingFirstBinding

class OnBoardingFirstFragment : Fragment() {

    companion object {
        private const val ARG_POSITION = "ARG_POSITION"
        fun getInstance (position : Int) = OnBoardingFirstFragment().apply {
            arguments = bundleOf(ARG_POSITION to position)
        }
    }

    private var _binding: FragmentOnBoardingFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoardingFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val position = requireArguments().getInt(ARG_POSITION)
        val onBoardingTexts = requireContext().resources.getStringArray(R.array.onboarding_texts)
        val images = arrayOf(R.drawable.first, R.drawable.second, R.drawable.third)
        with(binding) {
            onboardingText.text = onBoardingTexts[position]
            onboardingImage.setImageResource(images[position])
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}