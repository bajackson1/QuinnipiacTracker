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
import edu.quinnipiac.quinnipiactracker.data.Dining
import edu.quinnipiac.quinnipiactracker.data.DiningRoomDatabase
import edu.quinnipiac.quinnipiactracker.data.DiningViewModel
import edu.quinnipiac.quinnipiactracker.data.DiningViewModelFactory
import edu.quinnipiac.quinnipiactracker.databinding.FragmentDiningAddBinding

class DiningAddFragment : Fragment() {
    private var _binding: FragmentDiningAddBinding? = null
    private val binding get() = _binding!!

    private val presetDiningTitles = listOf(
        "Cafe",
        "Cafeteria",
        "Dining Hall",
        "Food Court",
        "Snack Bar"
    )

    private val presetDiningCoordinates: Map<String, LatLng> = mapOf(
        "Cafe" to LatLng(41.41883927955504, -72.89416933310599),
        "Cafeteria" to LatLng(41.415613766167226, -72.89486382827572),
        "Dining Hall" to LatLng(41.41950915640872, -72.89738883168646),
        "Food Court" to LatLng(41.4183663748876, -72.89706751864954),
        "Snack Bar" to LatLng(41.41955243718745, -72.89511512264953)
    )

    private lateinit var diningViewModel: DiningViewModel
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

        diningViewModel = ViewModelProvider(this, DiningViewModelFactory(DiningRoomDatabase.getDatabase(requireContext()).diningDao()))[DiningViewModel::class.java]

        diningViewModel.allDinings.observe(viewLifecycleOwner) { dinings ->
            existingDiningTitles.clear()
            dinings.forEach { dining ->
                existingDiningTitles.add(dining.itemName)
            }
        }

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, presetDiningTitles)
        binding.addDiningTitle.setAdapter(adapter)

        binding.saveDiningName.setOnClickListener {
            addDining()
        }

        binding.cancelDining.setOnClickListener {
            findNavController().navigateUp()
        }
    }

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