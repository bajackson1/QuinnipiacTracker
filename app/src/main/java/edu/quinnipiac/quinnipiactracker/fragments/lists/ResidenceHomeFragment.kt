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
import edu.quinnipiac.quinnipiactracker.data.residence.Residence
import edu.quinnipiac.quinnipiactracker.data.residence.ResidenceAdapter
import edu.quinnipiac.quinnipiactracker.data.residence.ResidenceRoomDatabase
import edu.quinnipiac.quinnipiactracker.data.residence.ResidenceViewModel
import edu.quinnipiac.quinnipiactracker.data.residence.ResidenceViewModelFactory
import edu.quinnipiac.quinnipiactracker.databinding.FragmentResidenceHomeBinding

class ResidenceHomeFragment : Fragment(R.layout.fragment_residence_home) {
    private var _binding: FragmentResidenceHomeBinding? = null
    private val binding get() = _binding!!

    private val residences = mutableListOf<Residence>()
    private lateinit var residenceAdapter: ResidenceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResidenceHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        residenceAdapter = ResidenceAdapter(residences) { residence ->
            val viewModel = ViewModelProvider(this, ResidenceViewModelFactory(ResidenceRoomDatabase.getDatabase(requireContext()).residenceDao()))[ResidenceViewModel::class.java]
            viewModel.deleteResidence(residence)
        }
        binding.residenceRecyclerView.adapter = residenceAdapter
        binding.residenceRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.addResidenceFab.setOnClickListener {
            findNavController().navigate(R.id.action_residenceHomeFragment_to_residenceAddFragment)
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_residenceHomeFragment_to_homeFragment)
        }

        val viewModel = ViewModelProvider(this, ResidenceViewModelFactory(ResidenceRoomDatabase.getDatabase(requireContext()).residenceDao()))[ResidenceViewModel::class.java]
        viewModel.allResidences.observe(viewLifecycleOwner) { residences ->
            this.residences.clear()
            this.residences.addAll(residences)
            residenceAdapter.notifyDataSetChanged()
            updateUI(residences)
        }
    }

    private fun updateUI(residences: List<Residence>) {
        if (residences.isNotEmpty()) {
            binding.logoImageView.visibility = View.GONE
            binding.logoTextView.visibility = View.GONE
            binding.residenceRecyclerView.visibility = View.VISIBLE
        } else {
            binding.logoImageView.visibility = View.VISIBLE
            binding.logoTextView.visibility = View.VISIBLE
            binding.residenceRecyclerView.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
