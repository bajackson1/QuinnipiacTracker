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
import edu.quinnipiac.quinnipiactracker.R

class HelpFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_help, container, false)
    }
}
