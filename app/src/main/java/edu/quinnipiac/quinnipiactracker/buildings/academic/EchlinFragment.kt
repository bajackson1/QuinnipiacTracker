/**
 * This file holds the functionality for the Echlin fragment.
 * This includes the back and favorite button.
 */
package edu.quinnipiac.quinnipiactracker.buildings.academic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.quinnipiac.quinnipiactracker.R
import edu.quinnipiac.quinnipiactracker.data.images.BuildingImage
import edu.quinnipiac.quinnipiactracker.data.images.SharedViewModel

class EchlinFragment : Fragment() {
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_echlin, container, false)

        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        // Setting back button
        val backButton = view.findViewById<ImageButton>(R.id.back_button)

        // Setting favorite button
        val favoriteButton = view.findViewById<ImageButton>(R.id.favorite_button)

        // Back button function
        backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        // Favorite button function
        favoriteButton.setOnClickListener {
            val echlinImage = BuildingImage(
                id = R.drawable.echlin,
                imageUrl = "https://example.com/echlin.jpg",
                title = "Echlin Building"
            )
            sharedViewModel.addBuildingFav(echlinImage)
        }

        return view
    }
}