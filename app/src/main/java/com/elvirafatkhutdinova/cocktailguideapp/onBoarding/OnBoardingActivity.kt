package com.elvirafatkhutdinova.cocktailguideapp.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.elvirafatkhutdinova.cocktailguideapp.databinding.ActivityOnboardingBinding
import com.google.android.material.tabs.TabLayoutMediator

private const val NUM_PAGES = 3

class OnBoardingActivity : FragmentActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        val view = binding.root

        viewPager = binding.viewPager
        val adapter = SlidePagerAdapter(this)
        viewPager.adapter = adapter
        val tabLayout = binding.tabsLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
        }.attach()
        setContentView(view)
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    private inner class SlidePagerAdapter(f: FragmentActivity) : FragmentStateAdapter(f) {

        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment = OnBoardingWelcomeFragment()
    }
}