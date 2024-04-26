/**
 * This file holds the functionality for the Student Center fragment.
 * This includes the back and favorite button.
 */
package edu.quinnipiac.quinnipiactracker.buildings.dining

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.quinnipiac.quinnipiactracker.R
import edu.quinnipiac.quinnipiactracker.data.images.DiningImage
import edu.quinnipiac.quinnipiactracker.data.images.SharedViewModel

class StudentCenterFragment : Fragment() {
    // Declare SharedViewModel instance
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_center, container, false)

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
            val cafImage = DiningImage(
                id = R.drawable.caf,
                imageUrl = "https://example.com/caf.jpg",
                title = "Caf Building"
            )
            sharedViewModel.addDiningFav(cafImage)
        }

        return view
    }
}