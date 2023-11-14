package com.elvirafatkhutdinova.cocktailguideapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Adapter
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elvirafatkhutdinova.cocktailguideapp.CocktailGuideApplication
import com.elvirafatkhutdinova.cocktailguideapp.R
import com.elvirafatkhutdinova.cocktailguideapp.databinding.FragmentCocktailsBinding
import com.elvirafatkhutdinova.cocktailguideapp.ui.adapters.CocktailsAdapter
import com.elvirafatkhutdinova.cocktailguideapp.ui.adapters.RecentCocktailsAdapter
import com.elvirafatkhutdinova.cocktailguideapp.ui.viewModels.CocktailViewModel
import com.elvirafatkhutdinova.cocktailguideapp.ui.viewModels.ViewModelFactory
import javax.inject.Inject

class CocktailsFragment : Fragment(R.layout.fragment_cocktails) {

    private var _binding: FragmentCocktailsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: CocktailViewModel
    private lateinit var recentCocktailsAdapter: RecentCocktailsAdapter
    private lateinit var cocktailsAdapter: CocktailsAdapter

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
        _binding = FragmentCocktailsBinding.inflate(layoutInflater, container, false)
        Log.d("CocktailsFragment", "ON_CREATED")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[CocktailViewModel::class.java]

        viewModel.eventNetworkError.observe(viewLifecycleOwner) { isNetworkError ->
            if (isNetworkError) onNetworkError()
        }

        setupRecyclerView(binding.rvCocktails)
        setupRecentRecyclerView(binding.rvRecentlyViewed)

        viewModel.getCocktailsAndFavorites().observe(viewLifecycleOwner) { cocktails ->
            cocktails?.apply {
                cocktailsAdapter.submitList(cocktails)
            }
        }

        viewModel.getRecentCocktails().observe(viewLifecycleOwner) { recents ->
            recents?.apply {
                if (this.isEmpty()) {
                    binding.recentlyViewedTxt.visibility = View.GONE
                    binding.rvRecentlyViewed.visibility = View.GONE
                } else {
                    binding.recentlyViewedTxt.visibility = View.VISIBLE
                    binding.rvRecentlyViewed.visibility = View.VISIBLE
                    recentCocktailsAdapter.submitList(recents)
                }
            }
        }

        binding.generateBtn.setOnClickListener {
            viewModel.getRandomCocktail {
                navigateToDetails(it)
            }
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        cocktailsAdapter = CocktailsAdapter()
        recyclerView.adapter = cocktailsAdapter
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        cocktailsAdapter.onItemClick { navigateToDetails(it) }
    }

    private fun setupRecentRecyclerView(recyclerView: RecyclerView) {
        recentCocktailsAdapter = RecentCocktailsAdapter()
        recyclerView.adapter = recentCocktailsAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recentCocktailsAdapter.onItemClick { navigateToDetails(it) }
    }

    private fun navigateToDetails(idCocktail: String) {
        findNavController().navigate(
            CocktailsFragmentDirections.actionCocktailListToCocktailDetail(idCocktail)
        )
    }

    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, R.string.network_error, Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        Log.d("CocktailsFragment", "ON_DESTROYED")
    }
}