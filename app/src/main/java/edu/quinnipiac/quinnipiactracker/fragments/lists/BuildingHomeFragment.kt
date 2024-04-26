/**
 * This fragment displays a list of buildings.
 * It allows the user to add new buildings and delete existing ones.
 */
package edu.quinnipiac.quinnipiactracker.fragments.lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import edu.quinnipiac.quinnipiactracker.R
import edu.quinnipiac.quinnipiactracker.data.academic.Building
import edu.quinnipiac.quinnipiactracker.data.academic.BuildingAdapter
import edu.quinnipiac.quinnipiactracker.data.academic.BuildingRoomDatabase
import edu.quinnipiac.quinnipiactracker.data.academic.BuildingViewModel
import edu.quinnipiac.quinnipiactracker.data.academic.BuildingViewModelFactory
import edu.quinnipiac.quinnipiactracker.databinding.FragmentBuildingHomeBinding

class BuildingHomeFragment : Fragment(R.layout.fragment_building_home) {
    private var _binding: FragmentBuildingHomeBinding? = null
    private val binding get() = _binding!!

    // List of buildings
    private val buildings = mutableListOf<Building>()
    private lateinit var buildingAdapter: BuildingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBuildingHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize building adapter
        buildingAdapter = BuildingAdapter(buildings) { building ->
            // Delete building
            val viewModel = ViewModelProvider(this, BuildingViewModelFactory(BuildingRoomDatabase.getDatabase(requireContext()).buildingDao()))[BuildingViewModel::class.java]
            viewModel.deleteBuilding(building)
        }
        binding.buildingRecyclerView.adapter = buildingAdapter
        binding.buildingRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Add building button click listener
        binding.addBuildingFab.setOnClickListener {
            findNavController().navigate(R.id.action_buildingHomeFragment_to_buildingAddFragment)
        }

        // Back button click listener
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_buildingHomeFragment_to_homeFragment)
        }

        // Observe all buildings and update the list
        val viewModel = ViewModelProvider(this, BuildingViewModelFactory(BuildingRoomDatabase.getDatabase(requireContext()).buildingDao()))[BuildingViewModel::class.java]
        viewModel.allBuildings.observe(viewLifecycleOwner) { buildings ->
            this.buildings.clear()
            this.buildings.addAll(buildings)
            buildingAdapter.notifyDataSetChanged()
            updateUI(buildings)
        }
    }

    // Update UI based on the list of buildings
    private fun updateUI(buildings: List<Building>) {
        // Setting item visibility
        if (buildings.isNotEmpty()) {
            binding.logoImageView.visibility = View.GONE
            binding.logoTextView.visibility = View.GONE
            binding.buildingRecyclerView.visibility = View.VISIBLE
        } else {
            binding.logoImageView.visibility = View.VISIBLE
            binding.logoTextView.visibility = View.VISIBLE
            binding.buildingRecyclerView.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}