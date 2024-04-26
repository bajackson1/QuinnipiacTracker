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
import edu.quinnipiac.quinnipiactracker.data.academic.Building
import edu.quinnipiac.quinnipiactracker.data.academic.BuildingRoomDatabase
import edu.quinnipiac.quinnipiactracker.data.academic.BuildingViewModel
import edu.quinnipiac.quinnipiactracker.data.academic.BuildingViewModelFactory
import edu.quinnipiac.quinnipiactracker.databinding.FragmentBuildingAddBinding

class BuildingAddFragment : Fragment() {
    // Binding for the fragment
    private var _binding: FragmentBuildingAddBinding? = null
    private val binding get() = _binding!!

    // List of preset building titles
    private val presetBuildingTitles = listOf(
        "Arnold Bernhard Library",
        "College of Arts and Sciences",
        "Communications, Computing and Engineering",
        "Echlin Center",
        "Lender School of Business",
        "Tator Hall"
    )

    // Map of preset building titles to their coordinates
    private val presetBuildingCoordinates: Map<String, LatLng> = mapOf(
        "Arnold Bernhard Library" to LatLng(41.41883927955504, -72.89416933310599),
        "College of Arts and Sciences" to LatLng(41.415613766167226, -72.89486382827572),
        "Communications, Computing and Engineering" to LatLng(41.41950915640872, -72.89738883168646),
        "Echlin Center" to LatLng(41.4183663748876, -72.89706751864954),
        "Lender School of Business" to LatLng(41.41955243718745, -72.89511512264953),
        "Tator Hall" to LatLng(41.4179680672367, -72.89576417435849)
    )

    // Building view model
    private lateinit var buildingViewModel: BuildingViewModel
    // Set of existing building titles
    private val existingBuildingTitles = mutableSetOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBuildingAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the building view model
        buildingViewModel = ViewModelProvider(this, BuildingViewModelFactory(BuildingRoomDatabase.getDatabase(requireContext()).buildingDao()))[BuildingViewModel::class.java]

        // Observe the list of buildings and update the existing building titles
        buildingViewModel.allBuildings.observe(viewLifecycleOwner) { buildings ->
            existingBuildingTitles.clear()
            buildings.forEach { building ->
                existingBuildingTitles.add(building.itemName)
            }
        }

        // Set the adapter for the building title dropdown
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, presetBuildingTitles)
        binding.addBuildingTitle.setAdapter(adapter)

        // Set the click listener for the save button
        binding.saveBuildingName.setOnClickListener {
            addBuilding()
        }

        // Set the click listener for the cancel button
        binding.cancelBuilding.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    // Function to add a new building
    private fun addBuilding() {
        // Get the building title from the text field
        val buildingTitle = binding.addBuildingTitle.text.toString()
        // Check if the title is in the preset list
        if (presetBuildingTitles.contains(buildingTitle)) {
            // Check if the title is already in the existing building titles
            if (existingBuildingTitles.contains(buildingTitle)) {
                // Show an error message if the title is already existing
                binding.addBuildingTitle.error = "You have already visited this building."
            } else {
                // Get the coordinates of the building from the preset map
                val coordinates = presetBuildingCoordinates[buildingTitle]
                if (coordinates != null) {
                    // Create a new building object and add it to the database
                    val newBuilding = Building(
                        itemName = buildingTitle,
                        latitude = coordinates.latitude,
                        longitude = coordinates.longitude
                    )
                    buildingViewModel.addNewBuilding(newBuilding)
                    existingBuildingTitles.add(buildingTitle)
                    // Navigate to the building home fragment
                    findNavController().navigate(R.id.action_buildingAddFragment_to_buildingHomeFragment)
                } else {
                    // Show an error message if the coordinates are not found
                    binding.addBuildingTitle.error = "Please select a valid building title from the list."
                }
            }
        } else {
            // Show an error message if the title is not in the preset list
            binding.addBuildingTitle.error = "Please select a valid building title from the list."
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}