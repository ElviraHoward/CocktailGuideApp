package com.elvirafatkhutdinova.cocktailguideapp.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elvirafatkhutdinova.cocktailguideapp.R
import com.elvirafatkhutdinova.cocktailguideapp.databinding.FragmentOnBoardingWelcomeBinding

class OnBoardingWelcomeFragment : Fragment() {

    private var _binding: FragmentOnBoardingWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoardingWelcomeBinding.inflate(inflater, container, false)
        val rootView = binding.root
        return rootView
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}