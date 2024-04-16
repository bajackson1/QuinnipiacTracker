package edu.quinnipiac.quinnipiactracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import edu.quinnipiac.quinnipiactracker.databinding.ActivityMainBinding
import android.content.res.ColorStateList

class MainActivity : AppCompatActivity() {

    // Declaring variable for view binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setting activity_main to content view
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Switching to HomeFragment (home screen)
        replaceFragment(HomeFragment())

        // Setting navigation bar icon color to null
        binding.bottomNavigationView.itemIconTintList = null

        // Making navigation bar Quinnipiac yellow color
        binding.bottomNavigationView.setBackgroundColor(resources.getColor(R.color.QUyellow, theme))

        // Setting state colors for navigation bar icons
        val iconColors = intArrayOf(
            resources.getColor(R.color.QUblue, theme),
            resources.getColor(R.color.QUblue, theme),
            resources.getColor(R.color.QUnavy, theme)
        )

        // Define the states for the color state list
        val iconStates = arrayOf(
            intArrayOf(android.R.attr.state_checked),
            intArrayOf(android.R.attr.state_pressed),
            intArrayOf()
        )

        // Creating icon and text color list for navigation bar
        val iconColorList = ColorStateList(iconStates, iconColors)

        // Setting icon and text color list for navigation bar
        binding.bottomNavigationView.itemIconTintList = iconColorList
        binding.bottomNavigationView.itemTextColor = iconColorList

        // Handling icon presses and screen switches
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    // Switches to Home screen
                    replaceFragment(HomeFragment())
                    binding.bottomNavigationView.menu.findItem(R.id.home).isChecked = true
                }
                R.id.info -> {
                    // Switches to Info screen
                    replaceFragment(InfoFragment())
                    binding.bottomNavigationView.menu.findItem(R.id.info).isChecked = true
                }
                R.id.faves -> {
                    // Switches to Favs screen
                    replaceFragment(FavesFragment())
                    binding.bottomNavigationView.menu.findItem(R.id.faves).isChecked = true
                }
                R.id.help -> {
                    // Switches to Help screen
                    replaceFragment(HelpFragment())
                    binding.bottomNavigationView.menu.findItem(R.id.help).isChecked = true
                }
                else -> {
                }
            }
            true
        }
    }

    // Replaces the frame_layout with given fragment
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}