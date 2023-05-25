package com.elvirafatkhutdinova.cocktailguideapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.elvirafatkhutdinova.cocktailguideapp.R
import com.elvirafatkhutdinova.cocktailguideapp.databinding.FragmentCocktailsBinding
import com.elvirafatkhutdinova.cocktailguideapp.ui.CocktailViewModel
import com.elvirafatkhutdinova.cocktailguideapp.ui.CocktailViewModelFactory
import com.elvirafatkhutdinova.cocktailguideapp.repository.Repository
import com.elvirafatkhutdinova.cocktailguideapp.ui.CocktailsAdapter

class CocktailsFragment : Fragment(R.layout.fragment_cocktails) {

    private var _binding : FragmentCocktailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel : CocktailViewModel
    private val cocktailsAdapter by lazy { CocktailsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCocktailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cocktailsRecyclerView.adapter = cocktailsAdapter
        binding.cocktailsRecyclerView.layoutManager = GridLayoutManager(activity, 2)

        val repository = Repository()
        val viewModelFactory = CocktailViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CocktailViewModel::class.java)
        viewModel.getCocktails()
        viewModel.cocktailResponse.observe(viewLifecycleOwner, Observer { response ->
            response
            cocktailsAdapter.setData(response.drinks)
        })

    }
}