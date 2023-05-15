package com.elvirafatkhutdinova.cocktailguideapp.UI.onBoarding

import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.elvirafatkhutdinova.cocktailguideapp.UI.CocktailsActivity
import com.elvirafatkhutdinova.cocktailguideapp.databinding.ActivityOnboardingBinding
import com.google.android.material.tabs.TabLayoutMediator

private const val NUM_PAGES = 3

class OnBoardingActivity : FragmentActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var myPageChangeCallback: ViewPager2.OnPageChangeCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        val view = binding.root

        viewPager = binding.viewPager
        val adapter = SlidePagerAdapter(this)
        viewPager.adapter = adapter
        val tabLayout = binding.tabsLayout
        TabLayoutMediator(tabLayout, viewPager) { _, _ ->
        }.attach()
        binding.skipButton.setOnClickListener {
            startMainActivity()
        }
        binding.getStartedButton.setOnClickListener {
            startMainActivity()
        }
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

        viewPager.registerOnPageChangeCallback(myPageChangeCallback)

        setContentView(view)
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewPager.unregisterOnPageChangeCallback(myPageChangeCallback)
    }

    private fun startMainActivity() {
        val intent = Intent(this, CocktailsActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private inner class SlidePagerAdapter(f: FragmentActivity) : FragmentStateAdapter(f) {

        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment =
            OnBoardingFirstFragment.getInstance(position)
    }
}