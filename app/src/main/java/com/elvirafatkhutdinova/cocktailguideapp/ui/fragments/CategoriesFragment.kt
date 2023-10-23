package com.elvirafatkhutdinova.cocktailguideapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.elvirafatkhutdinova.cocktailguideapp.CocktailGuideApplication
import com.elvirafatkhutdinova.cocktailguideapp.R
import com.elvirafatkhutdinova.cocktailguideapp.databinding.FragmentCategoriesBinding
import com.elvirafatkhutdinova.cocktailguideapp.ui.adapters.CategoriesAdapter
import com.elvirafatkhutdinova.cocktailguideapp.ui.viewModels.CategoryViewModel
import com.elvirafatkhutdinova.cocktailguideapp.ui.viewModels.ViewModelFactory
import javax.inject.Inject

class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: CategoryViewModel

    private val component by lazy {
        (requireActivity().application as CocktailGuideApplication).applicationComponent
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[CategoryViewModel::class.java]

        viewModel.categories.observe(viewLifecycleOwner) { categories ->
            categories?.apply {
                val categoriesAdapter = CategoriesAdapter(categories)
                binding.rvCategories.adapter = categoriesAdapter
                binding.rvCategories.layoutManager = LinearLayoutManager(activity)
                categoriesAdapter.onItemClick {
                    findNavController().navigate(
                        CategoriesFragmentDirections.actionCategoryListToCocktailListByCategory(
                            it
                        )
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}