package com.elvirafatkhutdinova.cocktailguideapp.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.elvirafatkhutdinova.cocktailguideapp.R
import com.elvirafatkhutdinova.cocktailguideapp.databinding.FragmentOnBoardingBinding
import com.elvirafatkhutdinova.cocktailguideapp.ui.CocktailsActivity
import com.elvirafatkhutdinova.cocktailguideapp.ui.adapters.OnBoardingAdapter
import com.elvirafatkhutdinova.cocktailguideapp.util.Constants.Companion.ON_BOARDING_SHOWN
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardingFragment : Fragment() {

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPager: ViewPager2
    private var myPageChangeCallback: ViewPager2.OnPageChangeCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)

        val sharedPreferences = requireActivity().getPreferences(FragmentActivity.MODE_PRIVATE)

        if (sharedPreferences.getBoolean(ON_BOARDING_SHOWN, false)) {
            findNavController().navigate(R.id.action_onBoardingFragment_to_cocktailList)
        } else {
            setupUI(sharedPreferences)
        }
        return binding.root
    }

    private fun setupUI(sharedPreferences: SharedPreferences) {

        viewPager = binding.viewPager
        val adapter = OnBoardingAdapter(requireContext())
        viewPager.adapter = adapter

        val tabLayout = binding.tabsLayout
        TabLayoutMediator(tabLayout, viewPager) { _, _ ->
        }.attach()

        setupClickListeners(sharedPreferences)
        setupViewPagerListener(adapter)

        (activity as CocktailsActivity).supportActionBar?.hide()
    }

    private fun setupClickListeners(sharedPreferences: SharedPreferences) {
        binding.skipButton.setOnClickListener {
            callHomeFragment(sharedPreferences)
        }
        binding.getStartedButton.setOnClickListener {
            callHomeFragment(sharedPreferences)
        }
    }

    private fun setupViewPagerListener(adapter: OnBoardingAdapter) {
        myPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (viewPager.currentItem == adapter.itemCount.minus(1)) {
                    binding.getStartedButton.isVisible = true
                    binding.skipButton.isVisible = false
                } else {
                    binding.getStartedButton.isVisible = false
                    binding.skipButton.isVisible = true
                }
            }
        }
        myPageChangeCallback?.apply {
            viewPager.registerOnPageChangeCallback(this)
        }
    }

    private fun onBoardingWasShown(sharedPreferences: SharedPreferences) {
        sharedPreferences.edit().apply {
            putBoolean(ON_BOARDING_SHOWN, true)
            apply()
        }
    }

    private fun callHomeFragment(sharedPreferences: SharedPreferences) {
        findNavController().navigate(R.id.action_onBoardingFragment_to_cocktailList)
        onBoardingWasShown(sharedPreferences)
    }

    override fun onStop() {
        super.onStop()
        (activity as CocktailsActivity).supportActionBar?.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        myPageChangeCallback?.apply {
            viewPager.unregisterOnPageChangeCallback(this)
        }
    }
}