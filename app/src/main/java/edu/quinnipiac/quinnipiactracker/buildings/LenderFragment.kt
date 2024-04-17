package edu.quinnipiac.quinnipiactracker.buildings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import edu.quinnipiac.quinnipiactracker.R

class LenderFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lender, container, false)

        // Setting back button
        val backButton = view.findViewById<Button>(R.id.back_button)

        // Back button function
        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return view
    }
}
