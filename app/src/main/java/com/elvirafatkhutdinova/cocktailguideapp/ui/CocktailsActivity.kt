package com.elvirafatkhutdinova.cocktailguideapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.elvirafatkhutdinova.cocktailguideapp.CocktailGuideApplication
import com.elvirafatkhutdinova.cocktailguideapp.R
import com.elvirafatkhutdinova.cocktailguideapp.databinding.ActivityCocktailsBinding

class CocktailsActivity : AppCompatActivity() {

    private var _binding : ActivityCocktailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val component by lazy {
        (application as CocktailGuideApplication).applicationComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component.inject(this)

        _binding = ActivityCocktailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(setOf(R.id.cocktailList, R.id.categoryList, R.id.favoriteList))
        navController.addOnDestinationChangedListener { _: NavController, destination: NavDestination, _: Bundle? ->
            binding.bottomNav.isVisible =
                appBarConfiguration.topLevelDestinations.contains(destination.id)
                        || destination.id == R.id.cocktailListByCategory
        }
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNav.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }
}