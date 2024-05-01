/**
 * The Help fragment holds the functionality for the help screen. This includes the different
 * buttons that show information about the app's creators and information in general.
 */
package edu.quinnipiac.quinnipiactracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.quinnipiac.quinnipiactracker.R

class HelpFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_help, container, false)

        val navigateToAppInfo = view.findViewById<View>(R.id.app_info_icon)
        navigateToAppInfo.setOnClickListener {
            findNavController().navigate(R.id.action_helpFragment_to_appInfoFragment)
        }

        val navigateToAppCredits = view.findViewById<View>(R.id.app_credits_icon)
        navigateToAppCredits.setOnClickListener {
            findNavController().navigate(R.id.action_helpFragment_to_appCreditsFragment)
        }

        val navigateToQUResources = view.findViewById<View>(R.id.qu_resources_icon)
        navigateToQUResources.setOnClickListener {
            findNavController().navigate(R.id.action_helpFragment_to_quResourcesFragment)
        }

        return view
    }
}
