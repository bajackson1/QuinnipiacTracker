/**
 * The Home fragment is what displays the map and the user's data for their visited locations.
 * The map data is all from the Google Maps API (which can be found on the Google Cloud platform.
 * We created an API key and used the instructions on the website to implement a working map.
 */

package edu.quinnipiac.quinnipiactracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import edu.quinnipiac.quinnipiactracker.R
import edu.quinnipiac.quinnipiactracker.data.academic.BuildingRoomDatabase
import edu.quinnipiac.quinnipiactracker.data.academic.BuildingViewModel
import edu.quinnipiac.quinnipiactracker.data.academic.BuildingViewModelFactory
import edu.quinnipiac.quinnipiactracker.data.dining.DiningRoomDatabase
import edu.quinnipiac.quinnipiactracker.data.dining.DiningViewModel
import edu.quinnipiac.quinnipiactracker.data.dining.DiningViewModelFactory
import edu.quinnipiac.quinnipiactracker.data.residence.ResidenceRoomDatabase
import edu.quinnipiac.quinnipiactracker.data.residence.ResidenceViewModel
import edu.quinnipiac.quinnipiactracker.data.residence.ResidenceViewModelFactory

class HomeFragment : Fragment(R.layout.fragment_home), OnMapReadyCallback {
    private lateinit var googleMap: GoogleMap
    private lateinit var buildingViewModel: BuildingViewModel
    private lateinit var diningViewModel: DiningViewModel
    private lateinit var residenceViewModel: ResidenceViewModel
    private var buildingCount: Int = 0
    private var diningCount: Int = 0
    private var residenceCount: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize the BuildingViewModel, DiningViewModel, and ResidenceViewModel
        buildingViewModel = ViewModelProvider(this, BuildingViewModelFactory(BuildingRoomDatabase.getDatabase(requireContext()).buildingDao()))[BuildingViewModel::class.java]
        diningViewModel = ViewModelProvider(this, DiningViewModelFactory(DiningRoomDatabase.getDatabase(requireContext()).diningDao()))[DiningViewModel::class.java]
        residenceViewModel = ViewModelProvider(this, ResidenceViewModelFactory(ResidenceRoomDatabase.getDatabase(requireContext()).residenceDao()))[ResidenceViewModel::class.java]

        // Using the SupportMapFragment in the layout and getting the map at the same time
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val navigateToBuildings = view.findViewById<View>(R.id.building_group)
        navigateToBuildings.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_buildingHomeFragment)
        }

        val navigateToDining = view.findViewById<View>(R.id.dining_group)
        navigateToDining.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_diningHomeFragment)
        }

        val navigateToResidences = view.findViewById<View>(R.id.residence_group)
        navigateToResidences.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_residenceHomeFragment)
        }

        val resetMapButton = view.findViewById<Button>(R.id.reset_map_button)
        resetMapButton.setOnClickListener {
            resetMap()
        }

        return view
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        // Focusing on Quinnipiac's Mount Carmel campus
        val focusedLocation = LatLng(41.419223, -72.893990)
        val zoomLevel = 16f

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(focusedLocation, zoomLevel))
        googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.clear()

        buildingViewModel.allBuildings.observe(viewLifecycleOwner) { buildings ->
            buildingCount = buildings.size
            updateBuildingCountText()
            for (building in buildings) {
                val coordinates = LatLng(building.latitude, building.longitude)
                googleMap.addMarker(MarkerOptions()
                    .position(coordinates)
                    .title(building.itemName)
                    .icon(BitmapDescriptorFactory.defaultMarker(213f)))
            }
        }

        diningViewModel.allDinings.observe(viewLifecycleOwner) { dinings ->
            diningCount = dinings.size
            updateDiningCountText()
            for (dining in dinings) {
                val coordinates = LatLng(dining.latitude, dining.longitude)
                googleMap.addMarker(MarkerOptions()
                    .position(coordinates)
                    .title(dining.itemName)
                    .icon(BitmapDescriptorFactory.defaultMarker(213f)))
            }
        }

        residenceViewModel.allResidences.observe(viewLifecycleOwner) { residences ->
            residenceCount = residences.size
            updateResidenceCountText()
            for (residence in residences) {
                val coordinates = LatLng(residence.latitude, residence.longitude)
                googleMap.addMarker(MarkerOptions()
                    .position(coordinates)
                    .title(residence.itemName)
                    .icon(BitmapDescriptorFactory.defaultMarker(213f)))
            }
        }
    }

    private fun updateBuildingCountText() {
        val buildingGroupView = view?.findViewById<LinearLayout>(R.id.building_group)
        val buildingCountTextView = buildingGroupView?.findViewById<TextView>(R.id.building_count)
        buildingCountTextView?.text = buildingCount.toString()
    }

    private fun updateDiningCountText() {
        val diningGroupView = view?.findViewById<LinearLayout>(R.id.dining_group)
        val diningCountTextView = diningGroupView?.findViewById<TextView>(R.id.dining_count)
        diningCountTextView?.text = diningCount.toString()
    }

    private fun updateResidenceCountText() {
        val residenceGroupView = view?.findViewById<LinearLayout>(R.id.residence_group)
        val residenceCountTextView = residenceGroupView?.findViewById<TextView>(R.id.residence_count)
        residenceCountTextView?.text = residenceCount.toString()
    }

    private fun resetMap() {
        val focusedLocation = LatLng(41.419223, -72.893990)
        val zoomLevel = 16f

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(focusedLocation, zoomLevel))
        googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.clear()

        buildingViewModel.allBuildings.observe(viewLifecycleOwner) { buildings ->
            buildingCount = buildings.size
            updateBuildingCountText()
            for (building in buildings) {
                val coordinates = LatLng(building.latitude, building.longitude)
                googleMap.addMarker(
                    MarkerOptions()
                        .position(coordinates)
                        .title(building.itemName)
                        .icon(BitmapDescriptorFactory.defaultMarker(213f))
                )
            }
        }

        diningViewModel.allDinings.observe(viewLifecycleOwner) { dinings ->
            diningCount = dinings.size
            updateDiningCountText()
            for (dining in dinings) {
                val coordinates = LatLng(dining.latitude, dining.longitude)
                googleMap.addMarker(
                    MarkerOptions()
                        .position(coordinates)
                        .title(dining.itemName)
                        .icon(BitmapDescriptorFactory.defaultMarker(213f))
                )
            }
        }

        residenceViewModel.allResidences.observe(viewLifecycleOwner) { residences ->
            residenceCount = residences.size
            updateResidenceCountText()
            for (residence in residences) {
                val coordinates = LatLng(residence.latitude, residence.longitude)
                googleMap.addMarker(
                    MarkerOptions()
                        .position(coordinates)
                        .title(residence.itemName)
                        .icon(BitmapDescriptorFactory.defaultMarker(213f))
                )
            }
        }
    }
}