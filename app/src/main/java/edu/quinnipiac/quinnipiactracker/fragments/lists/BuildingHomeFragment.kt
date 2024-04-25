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

        buildingAdapter = BuildingAdapter(buildings) { building ->
            val viewModel = ViewModelProvider(this, BuildingViewModelFactory(BuildingRoomDatabase.getDatabase(requireContext()).buildingDao()))[BuildingViewModel::class.java]
            viewModel.deleteBuilding(building)
        }
        binding.buildingRecyclerView.adapter = buildingAdapter
        binding.buildingRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.addBuildingFab.setOnClickListener {
            findNavController().navigate(R.id.action_buildingHomeFragment_to_buildingAddFragment)
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_buildingHomeFragment_to_homeFragment)
        }

        val viewModel = ViewModelProvider(this, BuildingViewModelFactory(BuildingRoomDatabase.getDatabase(requireContext()).buildingDao()))[BuildingViewModel::class.java]
        viewModel.allBuildings.observe(viewLifecycleOwner) { buildings ->
            this.buildings.clear()
            this.buildings.addAll(buildings)
            buildingAdapter.notifyDataSetChanged()
            updateUI(buildings)
        }
    }

    private fun updateUI(buildings: List<Building>) {
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