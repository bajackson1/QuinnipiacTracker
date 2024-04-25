/**
 * The Favs fragment is responsible for displaying the horizontal list of favorite images.
 * It uses a RecyclerView and a BuildingImageAdapter to manage the individual image views and
 * their data.
 */

package edu.quinnipiac.quinnipiactracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.quinnipiac.quinnipiactracker.data.SharedViewModel

class FavsFragment : Fragment() {
    private lateinit var buildingImageAdapter: BuildingFavsAdapter
    private lateinit var diningImageAdapter: DiningFavsAdapter
    private lateinit var residenceImageAdapter: ResidenceFavsAdapter
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_info, container, false)

        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        // Set up the horizontally scrolling building images
        val buildingImages = listOf(
            R.drawable.cas,
            R.drawable.cce,
            R.drawable.echlin,
            R.drawable.lender,
            R.drawable.library,
            R.drawable.tator
        )
        // Set up the horizontally scrolling dining images
        val diningImages = listOf(
            R.drawable.caf,
            R.drawable.rat
        )
        // Set up the horizontally scrolling residence images
        val residenceImages = listOf(
            R.drawable.commons,
            R.drawable.irma
        )

        // Finding a RecyclerView in the layout
        val buildingRecyclerView = view.findViewById<RecyclerView>(R.id.building_images_recycler_view)
        val diningRecyclerView = view.findViewById<RecyclerView>(R.id.dining_images_recycler_view)
        val residenceRecyclerView = view.findViewById<RecyclerView>(R.id.residence_images_recycler_view)

        // Making the layout of the RecyclerView a horizontal LinearLayoutManager
        buildingRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        diningRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        residenceRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

        // Creating an instance of the BuildingImageAdapter class
        buildingImageAdapter = BuildingFavsAdapter(
            buildingImages,
            ::navigateToCasFragment,
            ::navigateToCceFragment,
            ::navigateToEchlinFragment,
            ::navigateToLenderFragment,
            ::navigateToLibraryFragment,
            ::navigateToTatorFragment
        )
        diningImageAdapter = DiningFavsAdapter(
            diningImages,
            ::navigateToStudentCenterFragment,
            ::navigateToRatFragment
        )
        residenceImageAdapter = ResidenceFavsAdapter(
            residenceImages,
            ::navigateToCommonsFragment,
            ::navigateToIrmaFragment
        )

        // Setting it as the RecyclerView adapter
        buildingRecyclerView.adapter = buildingImageAdapter
        diningRecyclerView.adapter = diningImageAdapter
        residenceRecyclerView.adapter = residenceImageAdapter

        // Observe the favoriteItems LiveData
        sharedViewModel.favoriteItems.observe(viewLifecycleOwner) { favoriteItems ->
            updateFavoriteItems(favoriteItems)
        }

        return view
    }

    private fun updateFavoriteItems(favoriteItems: List<Int>) {
        // Update the BuildingFavsAdapter
        buildingImageAdapter.updateFavoriteItems(favoriteItems)

        // Update the DiningFavsAdapter
        diningImageAdapter.updateFavoriteItems(favoriteItems)

        // Update the ResidenceFavsAdapter
        residenceImageAdapter.updateFavoriteItems(favoriteItems)
    }

    // Function for CAS navigation
    private fun navigateToCasFragment() {
        findNavController().navigate(R.id.action_infoFragment_to_casFragment)
    }

    // Function for CCE navigation
    private fun navigateToCceFragment() {
        findNavController().navigate(R.id.action_infoFragment_to_cceFragment)
    }

    // Function for Echlin navigation
    private fun navigateToEchlinFragment() {
        findNavController().navigate(R.id.action_infoFragment_to_echlinFragment)
    }

    // Function for Lender navigation
    private fun navigateToLenderFragment() {
        findNavController().navigate(R.id.action_infoFragment_to_lenderFragment)
    }

    // Function for library navigation
    private fun navigateToLibraryFragment() {
        findNavController().navigate(R.id.action_infoFragment_to_libraryFragment)
    }

    // Function for Tator navigation
    private fun navigateToTatorFragment() {
        findNavController().navigate(R.id.action_infoFragment_to_tatorFragment)
    }

    // Function for Student Center navigation
    private fun navigateToStudentCenterFragment() {
        findNavController().navigate(R.id.action_infoFragment_to_studentCenterFragment)
    }

    // Function for Rat navigation
    private fun navigateToRatFragment() {
        findNavController().navigate(R.id.action_infoFragment_to_ratFragment)
    }

    // Function for Commons navigation
    private fun navigateToCommonsFragment() {
        findNavController().navigate(R.id.action_infoFragment_to_commonsFragment)
    }

    // Function for Irma navigation
    private fun navigateToIrmaFragment() {
        findNavController().navigate(R.id.action_infoFragment_to_irmaFragment)
    }
}