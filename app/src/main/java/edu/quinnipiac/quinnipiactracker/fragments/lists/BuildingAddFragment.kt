package edu.quinnipiac.quinnipiactracker.lists

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
import edu.quinnipiac.quinnipiactracker.data.Building
import edu.quinnipiac.quinnipiactracker.data.BuildingRoomDatabase
import edu.quinnipiac.quinnipiactracker.data.BuildingViewModel
import edu.quinnipiac.quinnipiactracker.data.BuildingViewModelFactory
import edu.quinnipiac.quinnipiactracker.databinding.FragmentBuildingAddBinding

class BuildingAddFragment : Fragment() {
    private var _binding: FragmentBuildingAddBinding? = null
    private val binding get() = _binding!!

    private val presetBuildingTitles = listOf(
        "Arnold Bernhard Library",
        "College of Arts and Sciences",
        "Communications, Computing and Engineering",
        "Echlin Center",
        "Lender School of Business",
        "Tator Hall"
    )

    private val presetBuildingCoordinates: Map<String, LatLng> = mapOf(
        "Arnold Bernhard Library" to LatLng(41.41883927955504, -72.89416933310599),
        "College of Arts and Sciences" to LatLng(41.415613766167226, -72.89486382827572),
        "Communications, Computing and Engineering" to LatLng(41.41950915640872, -72.89738883168646),
        "Echlin Center" to LatLng(41.4183663748876, -72.89706751864954),
        "Lender School of Business" to LatLng(41.41955243718745, -72.89511512264953),
        "Tator Hall" to LatLng(41.4179680672367, -72.89576417435849)
    )

    private lateinit var buildingViewModel: BuildingViewModel
    private val existingBuildingTitles = mutableSetOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBuildingAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buildingViewModel = ViewModelProvider(this, BuildingViewModelFactory(BuildingRoomDatabase.getDatabase(requireContext()).buildingDao()))[BuildingViewModel::class.java]

        buildingViewModel.allBuildings.observe(viewLifecycleOwner) { buildings ->
            existingBuildingTitles.clear()
            buildings.forEach { building ->
                existingBuildingTitles.add(building.itemName)
            }
        }

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, presetBuildingTitles)
        binding.addBuildingTitle.setAdapter(adapter)

        binding.saveBuildingName.setOnClickListener {
            addBuilding()
        }

        binding.cancelBuilding.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun addBuilding() {
        val buildingTitle = binding.addBuildingTitle.text.toString()
        if (presetBuildingTitles.contains(buildingTitle)) {
            if (existingBuildingTitles.contains(buildingTitle)) {
                binding.addBuildingTitle.error = "You have already visited this building."
            } else {
                val coordinates = presetBuildingCoordinates[buildingTitle]
                if (coordinates != null) {
                    val newBuilding = Building(
                        itemName = buildingTitle,
                        latitude = coordinates.latitude,
                        longitude = coordinates.longitude
                    )
                    buildingViewModel.addNewBuilding(newBuilding)
                    existingBuildingTitles.add(buildingTitle)
                    findNavController().navigate(R.id.action_buildingAddFragment_to_buildingHomeFragment)
                } else {
                    binding.addBuildingTitle.error = "Please select a valid building title from the list."
                }
            }
        } else {
            binding.addBuildingTitle.error = "Please select a valid building title from the list."
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}