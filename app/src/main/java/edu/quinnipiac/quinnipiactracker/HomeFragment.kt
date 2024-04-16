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
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

class HomeFragment : Fragment(R.layout.fragment_home), OnMapReadyCallback {
    private lateinit var googleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Using the SupportMapFragment in the layout and getting the map at the same time
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return view
    }

    override fun onMapReady(map: GoogleMap) {
        // Initiating a map instance
        googleMap = map

        // Focusing on Quinnipiac's Mount Carmel campus
        val focusedLocation = LatLng(41.419223, -72.893990)
        // Setting zoom level
        val zoomLevel = 16f

        // Moving the camera to the specified location
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(focusedLocation, zoomLevel))
        // Setting the map type to satellite
        googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        // Clearing the pins Google maps has on by default
        googleMap.clear()
    }
}