package com.elvirafatkhutdinova.cocktailguideapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.elvirafatkhutdinova.cocktailguideapp.CocktailGuideApplication
import com.elvirafatkhutdinova.cocktailguideapp.R
import com.elvirafatkhutdinova.cocktailguideapp.databinding.FragmentCocktailsByCategoryBinding
import com.elvirafatkhutdinova.cocktailguideapp.ui.adapters.CocktailsAdapter
import com.elvirafatkhutdinova.cocktailguideapp.ui.viewModels.CocktailViewModel
import com.elvirafatkhutdinova.cocktailguideapp.ui.viewModels.ViewModelFactory
import javax.inject.Inject

class CocktailsByCategoryFragment : Fragment(R.layout.fragment_cocktails_by_category) {

    private var _binding: FragmentCocktailsByCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CocktailViewModel
    private val args by navArgs<CocktailsByCategoryFragmentArgs>()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as CocktailGuideApplication).applicationComponent
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCocktailsByCategoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[CocktailViewModel::class.java]

        viewModel.getCocktailsByCategory(args.category)

        viewModel.cocktailList.observe(viewLifecycleOwner) { cocktailsAndFavorites ->
            if (args.category.isNotEmpty()) {
                val cocktailsAdapter = CocktailsAdapter(cocktailsAndFavorites)
                binding.rvCocktailsByCategory.adapter = cocktailsAdapter
                binding.rvCocktailsByCategory.layoutManager = GridLayoutManager(activity, 2)

                cocktailsAdapter.onItemClick {
                    findNavController().navigate(CocktailsByCategoryFragmentDirections.actionCocktailListByCategoryToCocktailDetailFromFragment(it))
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}