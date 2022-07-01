package com.example.testegit.core.ui

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.testegit.R

class MainActivity: AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private var flLoading: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setNavigationGraph()
        flLoading = findViewById(R.id.flLoading)
    }

    private fun setNavigationGraph() {
        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.container_main) as NavHostFragment
        navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.nav)
        navController.graph = navGraph
    }

    override fun onBackPressed() {
        navHostFragment.childFragmentManager.fragments.lastOrNull()?.let {
            if (!(it as BaseFragment<*>).onBackPressed())
                super.onBackPressed()
        } ?: kotlin.run {
            super.onBackPressed()
        }
    }

    fun showLoading(show: Boolean) {
        flLoading?.visibility = if (show) View.VISIBLE else View.GONE
    }
}