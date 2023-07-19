package com.elvirafatkhutdinova.cocktailguideapp.ui.fragments

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.elvirafatkhutdinova.cocktailguideapp.R
import com.elvirafatkhutdinova.cocktailguideapp.databinding.FragmentCocktailsByCategoryBinding
import com.elvirafatkhutdinova.cocktailguideapp.ui.CocktailViewModel
import com.elvirafatkhutdinova.cocktailguideapp.ui.adapters.CocktailsAdapter

class CocktailsByCategoryFragment : Fragment(R.layout.fragment_cocktails_by_category) {

    private var _binding: FragmentCocktailsByCategoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CocktailViewModel by activityViewModels()
    private val cocktailsAdapter by lazy { CocktailsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCocktailsByCategoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCocktailsByCategory.adapter = cocktailsAdapter
        binding.rvCocktailsByCategory.layoutManager = GridLayoutManager(activity, 2)

        val category = arguments?.getString("category") ?: ""
        (activity as AppCompatActivity).supportActionBar?.title= category

        val upArrow = ContextCompat.getDrawable(activity as AppCompatActivity, R.drawable.ic_arrow_back)
        upArrow?.setColorFilter(ContextCompat.getColor(activity as AppCompatActivity, R.color.black_text), PorterDuff.Mode.SRC_ATOP)
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(upArrow)

        viewModel.getCocktailsByCategory(category)
        viewModel.cocktailList.observe(viewLifecycleOwner) {
            if (category.isNotEmpty()) {
                cocktailsAdapter.setData(it)
            }
        }

        cocktailsAdapter.onItemClick {
            val bundle = Bundle().apply {
                putInt("idDrink", it)
            }
            findNavController().navigate(
                R.id.action_cocktailsByCategoryFragment_to_cocktailDetailFragment,
                bundle
            )
        }
    }

}