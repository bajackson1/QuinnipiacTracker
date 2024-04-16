/**
 * The Info fragment is responsible for displaying the horizontal list of images.
 * It uses a RecyclerView and a BuildingImageAdapter to manage the individual image views and
 * their data.
 */

package edu.quinnipiac.quinnipiactracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class InfoFragment : Fragment() {
    private lateinit var buildingImageAdapter: BuildingImageAdapter

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

        // Finding a RecyclerView in the layout
        val recyclerView = view.findViewById<RecyclerView>(R.id.building_images_recycler_view)

        // Making the layout of the RecyclerView a horizontal LinearLayoutManager
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

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
        // Setting it as the RecyclerView adapter
        recyclerView.adapter = buildingImageAdapter

        return view
    }

    private fun navigateToCasFragment() {
        findNavController().navigate(R.id.action_infoFragment_to_casFragment)
    }

    private fun navigateToCceFragment() {
        findNavController().navigate(R.id.action_infoFragment_to_cceFragment)
    }

    private fun navigateToEchlinFragment() {
        findNavController().navigate(R.id.action_infoFragment_to_echlinFragment)
    }

    private fun navigateToLenderFragment() {
        findNavController().navigate(R.id.action_infoFragment_to_lenderFragment)
    }

    private fun navigateToLibraryFragment() {
        findNavController().navigate(R.id.action_infoFragment_to_libraryFragment)
    }

    private fun navigateToTatorFragment() {
        findNavController().navigate(R.id.action_infoFragment_to_tatorFragment)
    }
}