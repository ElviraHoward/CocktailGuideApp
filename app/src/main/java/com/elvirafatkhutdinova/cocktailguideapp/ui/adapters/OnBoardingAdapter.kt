package com.elvirafatkhutdinova.cocktailguideapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elvirafatkhutdinova.cocktailguideapp.R
import com.elvirafatkhutdinova.cocktailguideapp.databinding.OnboardingItemBinding

class OnBoardingAdapter(val context: Context) :
    RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val images = arrayOf(R.drawable.first, R.drawable.second, R.drawable.third)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val itemBinding =
            OnboardingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OnBoardingViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        val onBoardingTexts = context.resources.getStringArray(R.array.onboarding_texts)
        with(holder) {
            itemBinding.onboardingImage.setImageResource(images[position])
            itemBinding.onboardingText.text = onBoardingTexts[position]
        }
    }

    inner class OnBoardingViewHolder(val itemBinding: OnboardingItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)
}