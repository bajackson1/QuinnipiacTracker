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
import edu.quinnipiac.quinnipiactracker.data.residence.Residence
import edu.quinnipiac.quinnipiactracker.data.residence.ResidenceRoomDatabase
import edu.quinnipiac.quinnipiactracker.data.residence.ResidenceViewModel
import edu.quinnipiac.quinnipiactracker.data.residence.ResidenceViewModelFactory
import edu.quinnipiac.quinnipiactracker.databinding.FragmentResidenceAddBinding

class ResidenceAddFragment : Fragment() {
    private var _binding: FragmentResidenceAddBinding? = null
    private val binding get() = _binding!!

    private val presetResidenceTitles = listOf(
        "Commons",
        "Complex - Bakke",
        "Complex - Founders",
        "Complex - Sahlin",
        "Dana",
        "Hill - 10s",
        "Hill - 20s",
        "Hill - 30s",
        "Hill - 40s",
        "Hill - 50s",
        "Hill - 60s",
        "Hill - 70s",
        "Irma",
        "Larson",
        "Ledges",
        "Mountainview",
        "Perlroth",
        "Troup",
        "Village - 400",
        "Village - 410",
        "Village - 420",
        "Village - 430",
        "Village - 440",
        "Village - 450",
        "Village - 460",
        "Village - 470",
        "Village - 480",
        "Village - 490",
        "Village - 500",
        "Village - 510",
        "Village - 520",
        "Village - 530",
        "Village - 540",
        "Village - 550",
        "Village - 560",
        "Village - 570",
        "Village - 580",
        "Village - 590",
        "Village - 600",
        "Village - 610",
        "Village - 620",
        "Village - 630",
        "Village - 640",
        "Village - 650",
        "Village - 660",
        "Village - 670",
    )

    private val presetResidenceCoordinates: Map<String, LatLng> = mapOf(
        "Commons" to LatLng(41.41725753367139, -72.89311894320355),
        "Complex - Bakke" to LatLng(41.419044087824226, -72.89030408410329),
        "Complex - Founders" to LatLng(41.41918456270053, -72.89003274347628),
        "Complex - Sahlin" to LatLng(41.41942549768992, -72.89024391233245),
        "Dana" to LatLng(41.41901907713136, -72.89214139593628),
        "Hill - 10s" to LatLng(41.41753593372772, -72.89240361079305),
        "Hill - 20s" to LatLng(41.41771730842882, -72.8923806238383),
        "Hill - 30s" to LatLng(41.41787245084976, -72.89234064652571),
        "Hill - 40s" to LatLng(41.41814664771949, -72.89248145707445),
        "Hill - 50s" to LatLng(41.41822609215602, -72.89231455179436),
        "Hill - 60s" to LatLng(41.418325022822685, -72.89214964537985),
        "Hill - 70s" to LatLng(41.418414210188466, -72.89197674350281),
        "Irma" to LatLng(41.41871857344952, -72.89263400118236),
        "Larson" to LatLng(41.419388515353404, -72.89130144915093),
        "Ledges" to LatLng(41.41944925014478, -72.88947542707443),
        "Mountainview" to LatLng(41.41982251679721, -72.88894930728966),
        "Perlroth" to LatLng(41.419093638377525, -72.8909309954143),
        "Troup" to LatLng(41.41968137839714, -72.89040124966878),
        "Village - 400" to LatLng(41.41870109464558, -72.89101883896787),
        "Village - 410" to LatLng(41.418468471469104, -72.89105851661255),
        "Village - 420" to LatLng(41.418236749098554, -72.89124969071872),
        "Village - 430" to LatLng(41.4180762560791, -72.8914685189409),
        "Village - 440" to LatLng(41.417862565316916, -72.8916777283401),
        "Village - 450" to LatLng(41.41776158041094, -72.8918689024462),
        //"Village - 460" to LatLng(41.41968137839714, -72.89040124966878),
        //"Village - 470" to LatLng(41.41968137839714, -72.89040124966878),
        //"Village - 480" to LatLng(41.41968137839714, -72.89040124966878),
        //"Village - 490" to LatLng(41.41968137839714, -72.89040124966878),
        //"Village - 500" to LatLng(41.41968137839714, -72.89040124966878),
        //"Village - 510" to LatLng(41.41968137839714, -72.89040124966878),
        //"Village - 520" to LatLng(41.41968137839714, -72.89040124966878),
        //"Village - 530" to LatLng(41.41968137839714, -72.89040124966878),
        //"Village - 540" to LatLng(41.41968137839714, -72.89040124966878),
        //"Village - 550" to LatLng(41.41968137839714, -72.89040124966878),
        //"Village - 560" to LatLng(41.41968137839714, -72.89040124966878),
        //"Village - 570" to LatLng(41.41968137839714, -72.89040124966878),
        //"Village - 580" to LatLng(41.41968137839714, -72.89040124966878),
        //"Village - 590" to LatLng(41.41968137839714, -72.89040124966878),
        //"Village - 600" to LatLng(41.41968137839714, -72.89040124966878),
        //"Village - 610" to LatLng(41.41968137839714, -72.89040124966878),
        //"Village - 620" to LatLng(41.41968137839714, -72.89040124966878),
        //"Village - 630" to LatLng(41.41968137839714, -72.89040124966878),
        //"Village - 640" to LatLng(41.41968137839714, -72.89040124966878),
        //"Village - 650" to LatLng(41.41968137839714, -72.89040124966878),
        //"Village - 660" to LatLng(41.41968137839714, -72.89040124966878),
        //"Village - 670" to LatLng(41.41968137839714, -72.89040124966878)
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