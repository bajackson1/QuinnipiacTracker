package edu.quinnipiac.quinnipiactracker.lists

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import edu.quinnipiac.quinnipiactracker.R
import edu.quinnipiac.quinnipiactracker.data.Building
import edu.quinnipiac.quinnipiactracker.data.BuildingAdapter
import edu.quinnipiac.quinnipiactracker.data.BuildingRoomDatabase
import edu.quinnipiac.quinnipiactracker.data.BuildingViewModel
import edu.quinnipiac.quinnipiactracker.data.BuildingViewModelFactory
import edu.quinnipiac.quinnipiactracker.databinding.FragmentBuildingHomeBinding

class BuildingHomeFragment : Fragment() {
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

    @SuppressLint("NotifyDataSetChanged")
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
            findNavController().navigateUp()
        }

        val viewModel = ViewModelProvider(this, BuildingViewModelFactory(BuildingRoomDatabase.getDatabase(requireContext()).buildingDao()))[BuildingViewModel::class.java]
        viewModel.allBuildings.observe(viewLifecycleOwner) { updatedBuildings ->
            buildings.clear()
            buildings.addAll(updatedBuildings)
            buildingAdapter.notifyDataSetChanged()
        }

        binding.logoImageView.visibility = if (buildings.isNotEmpty()) View.GONE else View.VISIBLE
        binding.logoTextView.visibility = if (buildings.isNotEmpty()) View.GONE else View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}