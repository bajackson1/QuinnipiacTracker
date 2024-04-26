/**
 * This file holds the functionality for the Commons fragment.
 * This includes the back and favorite button.
 */
package edu.quinnipiac.quinnipiactracker.buildings.residence

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.quinnipiac.quinnipiactracker.R
import edu.quinnipiac.quinnipiactracker.data.images.ResidenceImage
import edu.quinnipiac.quinnipiactracker.data.images.SharedViewModel

class CommonsFragment : Fragment() {
    // Declare SharedViewModel instance
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_commons, container, false)

        // Get SharedViewModel to store in sharedViewModel
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
            val commonsImage = ResidenceImage(
                id = R.drawable.commons,
                imageUrl = "https://example.com/commons.jpg",
                title = "Commons"
            )

            // Check if the building is already a favorite
            if (sharedViewModel.isResidenceFav(commonsImage)) {
                sharedViewModel.removeResidenceFav(commonsImage)
                Toast.makeText(requireContext(), "Removed from favorites", Toast.LENGTH_SHORT).show()
            } else {
                sharedViewModel.addResidenceFav(commonsImage)
                Toast.makeText(requireContext(), "Added to favorites", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}