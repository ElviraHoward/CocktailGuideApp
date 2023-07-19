package com.elvirafatkhutdinova.cocktailguideapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.elvirafatkhutdinova.cocktailguideapp.R
import com.elvirafatkhutdinova.cocktailguideapp.databinding.FragmentCategoriesBinding
import com.elvirafatkhutdinova.cocktailguideapp.ui.adapters.CategoriesAdapter
import com.elvirafatkhutdinova.cocktailguideapp.ui.CocktailViewModel

class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    private var _binding : FragmentCategoriesBinding? = null
    private val binding get() = _binding!!
    private val viewModel : CocktailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.categories.observe(viewLifecycleOwner) { categories ->
            categories?.apply {
                val categoriesAdapter = CategoriesAdapter(categories)
                binding.rvCategories.adapter = categoriesAdapter
                binding.rvCategories.layoutManager = LinearLayoutManager(activity)
                categoriesAdapter.onItemClick {
                    val bundle = Bundle().apply {
                        putString("category", it)
                    }
                    findNavController().navigate(R.id.action_categoriesFragment_to_cocktailsByCategoryFragment, bundle) }
            }
        }


    }
}