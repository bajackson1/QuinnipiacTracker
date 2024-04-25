/**
 * The Favs fragment is responsible for displaying the horizontal list of favorite images.
 * It uses a RecyclerView and a BuildingImageAdapter to manage the individual image views and
 * their data.
 */

package edu.quinnipiac.quinnipiactracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.quinnipiac.quinnipiactracker.R
import edu.quinnipiac.quinnipiactracker.data.images.BuildingFavsAdapter
import edu.quinnipiac.quinnipiactracker.data.images.DiningFavsAdapter
import edu.quinnipiac.quinnipiactracker.data.images.ResidenceFavsAdapter
import edu.quinnipiac.quinnipiactracker.data.images.SharedViewModel

class FavsFragment : Fragment() {
    private lateinit var buildingImageAdapter: BuildingFavsAdapter
    private lateinit var diningImageAdapter: DiningFavsAdapter
    private lateinit var residenceImageAdapter: ResidenceFavsAdapter
    private lateinit var sharedViewModel: SharedViewModel
    private var buildingFavsCount: Int = 0
    private var diningFavsCount: Int = 0
    private var residenceFavsCount: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favs, container, false)

        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        // Finding the RecyclerViews in the layout
        val buildingRecyclerView = view.findViewById<RecyclerView>(R.id.building_favs_recycler_view)
        val diningRecyclerView = view.findViewById<RecyclerView>(R.id.dining_favs_recycler_view)
        val residenceRecyclerView = view.findViewById<RecyclerView>(R.id.residence_favs_recycler_view)

        // Set the visibility of the RecyclerViews based on the data in the SharedViewModel
        if (sharedViewModel.buildingFavs.isNotEmpty()) {
            buildingRecyclerView.visibility = View.VISIBLE
            buildingFavsCount = sharedViewModel.buildingFavs.size
            updateBuildingFavsCountText()
        } else {
            buildingRecyclerView.visibility = View.GONE
        }

        if (sharedViewModel.diningFavs.isNotEmpty()) {
            diningRecyclerView.visibility = View.VISIBLE
            diningFavsCount = sharedViewModel.diningFavs.size
            updateDiningFavsCountText()
        } else {
            diningRecyclerView.visibility = View.GONE
        }

        if (sharedViewModel.residenceFavs.isNotEmpty()) {
            residenceRecyclerView.visibility = View.VISIBLE
            residenceFavsCount = sharedViewModel.residenceFavs.size
            updateResidenceFavsCountText()
        } else {
            residenceRecyclerView.visibility = View.GONE
        }

        // Make the layout of the RecyclerViews a horizontal LinearLayoutManager
        buildingRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        diningRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        residenceRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

        // Create the click listener maps
        val buildingClickListeners = mapOf(
            R.drawable.cas to ::navigateToCasFragment,
            R.drawable.cce to ::navigateToCceFragment,
            R.drawable.echlin to ::navigateToEchlinFragment,
            R.drawable.lender to ::navigateToLenderFragment,
            R.drawable.library to ::navigateToLibraryFragment,
            R.drawable.tator to ::navigateToTatorFragment
        )

        val diningClickListeners = mapOf(
            R.drawable.caf to ::navigateToStudentCenterFragment,
            R.drawable.rat to ::navigateToRatFragment
        )

        val residenceClickListeners = mapOf(
            R.drawable.commons to ::navigateToCommonsFragment,
            R.drawable.complex to ::navigateToComplexFragment,
            R.drawable.dana to ::navigateToDanaFragment,
            R.drawable.hill to ::navigateToHillFragment,
            R.drawable.irma to ::navigateToIrmaFragment,
            R.drawable.larson to ::navigateToLarsonFragment,
            R.drawable.ledges to ::navigateToLedgesFragment,
            R.drawable.mountainview to ::navigateToMountainviewFragment,
            R.drawable.perl to ::navigateToPerlFragment,
            R.drawable.troup to ::navigateToTroupFragment,
            R.drawable.village to ::navigateToVillageFragment
        )

        // Create the adapters with the data from the SharedViewModel
        buildingImageAdapter = BuildingFavsAdapter(
            sharedViewModel.buildingFavs,
            buildingClickListeners
        )
        diningImageAdapter = DiningFavsAdapter(
            sharedViewModel.diningFavs,
            diningClickListeners
        )
        residenceImageAdapter = ResidenceFavsAdapter(
            sharedViewModel.residenceFavs,
            residenceClickListeners
        )

        // Set the adapters to the RecyclerViews
        buildingRecyclerView.adapter = buildingImageAdapter
        diningRecyclerView.adapter = diningImageAdapter
        residenceRecyclerView.adapter = residenceImageAdapter

        // Observe the count changes and update the TextViews
        sharedViewModel.buildingFavsCount.observe(viewLifecycleOwner) { updateBuildingFavsCountText() }
        sharedViewModel.diningFavsCount.observe(viewLifecycleOwner) { updateDiningFavsCountText() }
        sharedViewModel.residenceFavsCount.observe(viewLifecycleOwner) { updateResidenceFavsCountText() }

        return view
    }

    // Function for CAS navigation
    private fun navigateToCasFragment() {
        findNavController().navigate(R.id.action_favsFragment_to_casFragment)
    }

    // Function for CCE navigation
    private fun navigateToCceFragment() {
        findNavController().navigate(R.id.action_favsFragment_to_cceFragment)
    }

    // Function for Echlin navigation
    private fun navigateToEchlinFragment() {
        findNavController().navigate(R.id.action_favsFragment_to_echlinFragment)
    }

    // Function for Lender navigation
    private fun navigateToLenderFragment() {
        findNavController().navigate(R.id.action_favsFragment_to_lenderFragment)
    }

    // Function for library navigation
    private fun navigateToLibraryFragment() {
        findNavController().navigate(R.id.action_favsFragment_to_libraryFragment)
    }

    // Function for Tator navigation
    private fun navigateToTatorFragment() {
        findNavController().navigate(R.id.action_favsFragment_to_tatorFragment)
    }

    // Function for Student Center navigation
    private fun navigateToStudentCenterFragment() {
        findNavController().navigate(R.id.action_favsFragment_to_studentCenterFragment)
    }

    // Function for Rat navigation
    private fun navigateToRatFragment() {
        findNavController().navigate(R.id.action_favsFragment_to_ratFragment)
    }

    // Function for Commons navigation
    private fun navigateToCommonsFragment() {
        findNavController().navigate(R.id.action_favsFragment_to_commonsFragment)
    }

    // Function for Complex navigation
    private fun navigateToComplexFragment() {
        findNavController().navigate(R.id.action_favsFragment_to_complexFragment)
    }

    // Function for Dana navigation
    private fun navigateToDanaFragment() {
        findNavController().navigate(R.id.action_favsFragment_to_danaFragment)
    }

    // Function for Hill navigation
    private fun navigateToHillFragment() {
        findNavController().navigate(R.id.action_favsFragment_to_hillFragment)
    }

    // Function for Irma navigation
    private fun navigateToIrmaFragment() {
        findNavController().navigate(R.id.action_infoFragment_to_irmaFragment)
    }

    // Function for Larson navigation
    private fun navigateToLarsonFragment() {
        findNavController().navigate(R.id.action_favsFragment_to_larsonFragment)
    }

    // Function for Ledges navigation
    private fun navigateToLedgesFragment() {
        findNavController().navigate(R.id.action_favsFragment_to_ledgesFragment)
    }

    // Function for Mountainview navigation
    private fun navigateToMountainviewFragment() {
        findNavController().navigate(R.id.action_favsFragment_to_mountainviewFragment)
    }

    // Function for Perl navigation
    private fun navigateToPerlFragment() {
        findNavController().navigate(R.id.action_favsFragment_to_perlFragment)
    }

    // Function for Troup navigation
    private fun navigateToTroupFragment() {
        findNavController().navigate(R.id.action_favsFragment_to_troupFragment)
    }

    // Function for Village navigation
    private fun navigateToVillageFragment() {
        findNavController().navigate(R.id.action_favsFragment_to_villageFragment)
    }

    private fun updateBuildingFavsCountText() {
        val buildingFavsCountTextView = view?.findViewById<TextView>(R.id.building_favs_count)
        buildingFavsCountTextView?.text = buildingFavsCount.toString()
    }

    private fun updateDiningFavsCountText() {
        val diningFavsCountTextView = view?.findViewById<TextView>(R.id.dining_favs_count)
        diningFavsCountTextView?.text = diningFavsCount.toString()
    }

    private fun updateResidenceFavsCountText() {
        val residenceFavsCountTextView = view?.findViewById<TextView>(R.id.residence_favs_count)
        residenceFavsCountTextView?.text = residenceFavsCount.toString()
    }
}