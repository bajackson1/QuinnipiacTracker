/**
 * The Info fragment is responsible for displaying the horizontal list of images.
 * It uses a RecyclerView and a BuildingImageAdapter to manage the individual image views and
 * their data.
 */

package edu.quinnipiac.quinnipiactracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.quinnipiac.quinnipiactracker.data.images.BuildingImageAdapter
import edu.quinnipiac.quinnipiactracker.data.images.DiningImageAdapter
import edu.quinnipiac.quinnipiactracker.R
import edu.quinnipiac.quinnipiactracker.data.images.ResidenceImageAdapter

class InfoFragment : Fragment() {
    private lateinit var buildingImageAdapter: BuildingImageAdapter
    private lateinit var diningImageAdapter: DiningImageAdapter
    private lateinit var residenceImageAdapter: ResidenceImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_info, container, false)

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
        buildingImageAdapter = BuildingImageAdapter(
            buildingImages,
            ::navigateToCasFragment,
            ::navigateToCceFragment,
            ::navigateToEchlinFragment,
            ::navigateToLenderFragment,
            ::navigateToLibraryFragment,
            ::navigateToTatorFragment
        )
        diningImageAdapter = DiningImageAdapter(
            diningImages,
            ::navigateToStudentCenterFragment,
            ::navigateToRatFragment

        )
        residenceImageAdapter = ResidenceImageAdapter(
            residenceImages,
            ::navigateToCommonsFragment,
            ::navigateToIrmaFragment
        )

        // Setting it as the RecyclerView adapter
        buildingRecyclerView.adapter = buildingImageAdapter
        diningRecyclerView.adapter = diningImageAdapter
        residenceRecyclerView.adapter = residenceImageAdapter

        return view
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