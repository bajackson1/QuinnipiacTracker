package edu.quinnipiac.quinnipiactracker.lists

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import edu.quinnipiac.quinnipiactracker.R
import edu.quinnipiac.quinnipiactracker.data.Residence
import edu.quinnipiac.quinnipiactracker.data.ResidenceAdapter
import edu.quinnipiac.quinnipiactracker.data.ResidenceRoomDatabase
import edu.quinnipiac.quinnipiactracker.data.ResidenceViewModel
import edu.quinnipiac.quinnipiactracker.data.ResidenceViewModelFactory
import edu.quinnipiac.quinnipiactracker.databinding.FragmentResidenceHomeBinding

class ResidenceHomeFragment : Fragment() {
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

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        residenceAdapter = ResidenceAdapter(residences) { residence ->
            val viewModel = ViewModelProvider(this, ResidenceViewModelFactory(ResidenceRoomDatabase.getDatabase(requireContext()).residenceDao()))[ResidenceViewModel::class.java]
            viewModel.deleteResidence(residence)
        }
        binding.residenceRecyclerViews.adapter = residenceAdapter
        binding.residenceRecyclerViews.layoutManager = LinearLayoutManager(requireContext())

        binding.addResidenceFab.setOnClickListener {
            findNavController().navigate(R.id.action_residenceHomeFragment_to_residenceAddFragment)
        }

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        val viewModel = ViewModelProvider(this, ResidenceViewModelFactory(ResidenceRoomDatabase.getDatabase(requireContext()).residenceDao()))[ResidenceViewModel::class.java]
        viewModel.allResidences.observe(viewLifecycleOwner) { updatedResidences ->
            residences.clear()
            residences.addAll(updatedResidences)
            residenceAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}