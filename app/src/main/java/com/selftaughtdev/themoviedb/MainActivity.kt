package com.selftaughtdev.themoviedb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.selftaughtdev.themoviedb.databinding.ActivityMainBinding
import com.selftaughtdev.themoviedb.databinding.NavHeaderBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        setSupportActionBar(binding.toolbar)

        val topLevelDestination = setOf(R.id.home, R.id.movies, R.id.series)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(topLevelDestination, binding.drawerLayout)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.navigationView.setupWithNavController(navController)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            val drawerLockMode = if (destination.id !in topLevelDestination)
                DrawerLayout.LOCK_MODE_LOCKED_CLOSED
            else
                DrawerLayout.LOCK_MODE_UNLOCKED
            binding.drawerLayout.setDrawerLockMode(drawerLockMode)
        }

        /**
         *  Setup Header View
         * **/
        val headerView = binding.navigationView.getHeaderView(0)
        val headerBinding = NavHeaderBinding.bind(headerView)
        headerBinding.lifecycleOwner = this
    }
}