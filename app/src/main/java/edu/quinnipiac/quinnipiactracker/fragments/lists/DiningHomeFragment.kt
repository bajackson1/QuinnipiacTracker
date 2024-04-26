/**
 * This fragment displays a list of dining locations.
 * It allows the user to add new dining locations and delete existing ones.
 */
package edu.quinnipiac.quinnipiactracker.fragments.lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import edu.quinnipiac.quinnipiactracker.R
import edu.quinnipiac.quinnipiactracker.data.dining.Dining
import edu.quinnipiac.quinnipiactracker.data.dining.DiningAdapter
import edu.quinnipiac.quinnipiactracker.data.dining.DiningRoomDatabase
import edu.quinnipiac.quinnipiactracker.data.dining.DiningViewModel
import edu.quinnipiac.quinnipiactracker.data.dining.DiningViewModelFactory
import edu.quinnipiac.quinnipiactracker.databinding.FragmentDiningHomeBinding

class DiningHomeFragment : Fragment(R.layout.fragment_dining_home) {
    private var _binding: FragmentDiningHomeBinding? = null
    private val binding get() = _binding!!

    // List of dining locations
    private val dinings = mutableListOf<Dining>()
    private lateinit var diningAdapter: DiningAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiningHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize dining adapter
        diningAdapter = DiningAdapter(dinings) { dining ->
            // Delete dining location
            val viewModel = ViewModelProvider(this, DiningViewModelFactory(DiningRoomDatabase.getDatabase(requireContext()).diningDao()))[DiningViewModel::class.java]
            viewModel.deleteDining(dining)
        }
        binding.diningRecyclerView.adapter = diningAdapter
        binding.diningRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Add dining button click listener
        binding.addDiningFab.setOnClickListener {
            findNavController().navigate(R.id.action_diningHomeFragment_to_diningAddFragment)
        }

        // Back button click listener
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_diningHomeFragment_to_homeFragment)
        }

        // Observe all dining locations and update the list
        val viewModel = ViewModelProvider(this, DiningViewModelFactory(DiningRoomDatabase.getDatabase(requireContext()).diningDao()))[DiningViewModel::class.java]
        viewModel.allDinings.observe(viewLifecycleOwner) { dinings ->
            this.dinings.clear()
            this.dinings.addAll(dinings)
            diningAdapter.notifyDataSetChanged()
            updateUI(dinings)
        }
    }

    // Update UI based on the list of dining locations
    private fun updateUI(dinings: List<Dining>) {
        // Setting item visibility
        if (dinings.isNotEmpty()) {
            binding.logoImageView.visibility = View.GONE
            binding.logoTextView.visibility = View.GONE
            binding.diningRecyclerView.visibility = View.VISIBLE
        } else {
            binding.logoImageView.visibility = View.VISIBLE
            binding.logoTextView.visibility = View.VISIBLE
            binding.diningRecyclerView.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}