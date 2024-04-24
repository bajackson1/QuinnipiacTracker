/**
 * The Home fragment is what displays the map and the user's data for their visited locations.
 * The map data is all from the Google Maps API (which can be found on the Google Cloud platform.
 * We created an API key and used the instructions on the website to implement a working map.
 */

package edu.quinnipiac.quinnipiactracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import edu.quinnipiac.quinnipiactracker.data.BuildingRoomDatabase
import edu.quinnipiac.quinnipiactracker.data.BuildingViewModel
import edu.quinnipiac.quinnipiactracker.data.BuildingViewModelFactory

class HomeFragment : Fragment(R.layout.fragment_home), OnMapReadyCallback {
    private lateinit var googleMap: GoogleMap
    private lateinit var buildingViewModel: BuildingViewModel
    private var buildingCount: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize the BuildingViewModel
        buildingViewModel = ViewModelProvider(this, BuildingViewModelFactory(BuildingRoomDatabase.getDatabase(requireContext()).buildingDao()))[BuildingViewModel::class.java]

        // Using the SupportMapFragment in the layout and getting the map at the same time
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val navigateToBuildings = view.findViewById<View>(R.id.building_group)
        navigateToBuildings.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_buildingHomeFragment)
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

        // Observe the buildings in the database and add markers for them
        buildingViewModel.allBuildings.observe(viewLifecycleOwner) { buildings ->
            buildingCount = buildings.size
            updateBuildingCountText()
            for (building in buildings) {
                val coordinates = LatLng(building.latitude, building.longitude)
                googleMap.addMarker(MarkerOptions()
                    .position(coordinates)
                    .title(building.itemName))
            }
        }
    }

    private fun updateBuildingCountText() {
        val buildingGroupView = view?.findViewById<LinearLayout>(R.id.building_group)
        val buildingCountTextView = buildingGroupView?.findViewById<TextView>(R.id.building_count)
        buildingCountTextView?.text = buildingCount.toString()
    }
}