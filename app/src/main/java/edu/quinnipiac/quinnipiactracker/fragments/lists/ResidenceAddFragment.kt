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
import edu.quinnipiac.quinnipiactracker.data.Residence
import edu.quinnipiac.quinnipiactracker.data.ResidenceRoomDatabase
import edu.quinnipiac.quinnipiactracker.data.ResidenceViewModel
import edu.quinnipiac.quinnipiactracker.data.ResidenceViewModelFactory
import edu.quinnipiac.quinnipiactracker.databinding.FragmentResidenceAddBinding

class ResidenceAddFragment : Fragment() {
    private var _binding: FragmentResidenceAddBinding? = null
    private val binding get() = _binding!!

    private val presetResidenceTitles = listOf(
        "Ledges",
        "Mountainview",
        "Westview",
        "Commons",
        "Irmagarde Tator Hall",
        "Crescent"
    )

    private val presetResidenceCoordinates: Map<String, LatLng> = mapOf(
        "Ledges" to LatLng(41.41883927955504, -72.89416933310599),
        "Mountainview" to LatLng(41.415613766167226, -72.89486382827572),
        "Westview" to LatLng(41.41950915640872, -72.89738883168646),
        "Commons" to LatLng(41.4183663748876, -72.89706751864954),
        "Irmagarde Tator Hall" to LatLng(41.41955243718745, -72.89511512264953),
        "Crescent" to LatLng(41.4179680672367, -72.89576417435849)
    )

    private lateinit var residenceViewModel: ResidenceViewModel
    private val existingResidenceTitles = mutableSetOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResidenceAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        residenceViewModel = ViewModelProvider(this, ResidenceViewModelFactory(ResidenceRoomDatabase.getDatabase(requireContext()).residenceDao()))[ResidenceViewModel::class.java]

        residenceViewModel.allResidences.observe(viewLifecycleOwner) { residences ->
            existingResidenceTitles.clear()
            residences.forEach { residence ->
                existingResidenceTitles.add(residence.itemName)
            }
        }

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, presetResidenceTitles)
        binding.addResidenceTitle.setAdapter(adapter)

        binding.saveResidenceName.setOnClickListener {
            addResidence()
        }

        binding.cancelResidence.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun addResidence() {
        val residenceTitle = binding.addResidenceTitle.text.toString()
        if (presetResidenceTitles.contains(residenceTitle)) {
            if (existingResidenceTitles.contains(residenceTitle)) {
                binding.addResidenceTitle.error = "You have already visited this residence."
            } else {
                val coordinates = presetResidenceCoordinates[residenceTitle]
                if (coordinates != null) {
                    val newResidence = Residence(
                        itemName = residenceTitle,
                        latitude = coordinates.latitude,
                        longitude = coordinates.longitude
                    )
                    residenceViewModel.addNewResidence(newResidence)
                    existingResidenceTitles.add(residenceTitle)
                    findNavController().navigate(R.id.action_residenceAddFragment_to_residenceHomeFragment)
                } else {
                    binding.addResidenceTitle.error = "Please select a valid residence title from the list."
                }
            }
        } else {
            binding.addResidenceTitle.error = "Please select a valid residence title from the list."
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}