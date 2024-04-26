/**
 * This fragment adds new buildings to the database.
 * It allows the user to select a building title from a preset list and save it to the database.
 * If the building title is already in the database, it shows an error message.
 */
package edu.quinnipiac.quinnipiactracker.fragments.lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.model.LatLng
import edu.quinnipiac.quinnipiactracker.R
import edu.quinnipiac.quinnipiactracker.data.dining.Dining
import edu.quinnipiac.quinnipiactracker.data.dining.DiningRoomDatabase
import edu.quinnipiac.quinnipiactracker.data.dining.DiningViewModel
import edu.quinnipiac.quinnipiactracker.data.dining.DiningViewModelFactory
import edu.quinnipiac.quinnipiactracker.databinding.FragmentDiningAddBinding

class DiningAddFragment : Fragment() {
    // Binding for the fragment
    private var _binding: FragmentDiningAddBinding? = null
    private val binding get() = _binding!!

    // List of preset dining titles
    private val presetDiningTitles = listOf(
        "Student Center",
        "Bobcat Den/The Rat"
    )

    // Map of preset dining titles to their coordinates
    private val presetDiningCoordinates: Map<String, LatLng> = mapOf(
        "Student Center" to LatLng(41.418170208397335, -72.89490294911462),
        "Bobcat Den/The Rat" to LatLng(41.41875670802813, -72.89178587199964)
    )

    // Dining view model
    private lateinit var diningViewModel: DiningViewModel

    // Set of existing dining titles
    private val existingDiningTitles = mutableSetOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiningAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize dining view model
        diningViewModel = ViewModelProvider(this, DiningViewModelFactory(DiningRoomDatabase.getDatabase(requireContext()).diningDao()))[DiningViewModel::class.java]

        // Observe all dining locations and update the existing titles set
        diningViewModel.allDinings.observe(viewLifecycleOwner) { dinings ->
            existingDiningTitles.clear()
            dinings.forEach { dining ->
                existingDiningTitles.add(dining.itemName)
            }
        }

        // Set up adapter for preset dining titles
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, presetDiningTitles)
        binding.addDiningTitle.setAdapter(adapter)

        // Save button click listener
        binding.saveDiningName.setOnClickListener {
            addDining()
        }

        // Cancel button click listener
        binding.cancelDining.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    // Add a new dining location to the database
    private fun addDining() {
        val diningTitle = binding.addDiningTitle.text.toString()
        if (presetDiningTitles.contains(diningTitle)) {
            if (existingDiningTitles.contains(diningTitle)) {
                binding.addDiningTitle.error = "You have already visited this dining hall."
            } else {
                val coordinates = presetDiningCoordinates[diningTitle]
                if (coordinates != null) {
                    val newDining = Dining(
                        itemName = diningTitle,
                        latitude = coordinates.latitude,
                        longitude = coordinates.longitude
                    )
                    diningViewModel.addNewDining(newDining)
                    existingDiningTitles.add(diningTitle)
                    findNavController().navigate(R.id.action_diningAddFragment_to_diningHomeFragment)
                } else {
                    binding.addDiningTitle.error = "Please select a valid dining hall title from the list."
                }
            }
        } else {
            binding.addDiningTitle.error = "Please select a valid dining hall title from the list."
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}