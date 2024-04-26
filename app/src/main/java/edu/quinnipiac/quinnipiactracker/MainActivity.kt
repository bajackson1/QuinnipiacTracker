/**
 * The MainActivity is the activity that all of the app's fragments are in.
 * This initializes app features such as the navigation bar and the NavController so that the user
 * can navigate from fragment to fragment.
 */
package edu.quinnipiac.quinnipiactracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    // Initializing NavController to navigate between fragments
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Setting original content to main activity xml
        setContentView(R.layout.activity_main)

        // Getting the NavController instance from the layout
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController
        // Getting the bottomNavigationView (bottom navigation bar) from the layout
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        // Getting the NavController and the bottomNavigationView to work together
        setupWithNavController(bottomNavigationView, navController)
    }
}