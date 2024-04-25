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
import edu.quinnipiac.quinnipiactracker.data.Dining
import edu.quinnipiac.quinnipiactracker.data.DiningAdapter
import edu.quinnipiac.quinnipiactracker.data.DiningRoomDatabase
import edu.quinnipiac.quinnipiactracker.data.DiningViewModel
import edu.quinnipiac.quinnipiactracker.data.DiningViewModelFactory
import edu.quinnipiac.quinnipiactracker.databinding.FragmentDiningHomeBinding

class DiningHomeFragment : Fragment() {
    private var _binding: FragmentDiningHomeBinding? = null
    private val binding get() = _binding!!

    private val dinings = mutableListOf<Dining>()
    private lateinit var diningAdapter: DiningAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiningHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        diningAdapter = DiningAdapter(dinings) { dining ->
            val viewModel = ViewModelProvider(this, DiningViewModelFactory(DiningRoomDatabase.getDatabase(requireContext()).diningDao()))[DiningViewModel::class.java]
            viewModel.deleteDining(dining)
        }
        binding.diningRecyclerView.adapter = diningAdapter
        binding.diningRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.addDiningFab.setOnClickListener {
            findNavController().navigate(R.id.action_diningHomeFragment_to_diningAddFragment)
        }

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        // Observe the dinings from the ViewModel and update the adapter
        val viewModel = ViewModelProvider(this, DiningViewModelFactory(DiningRoomDatabase.getDatabase(requireContext()).diningDao()))[DiningViewModel::class.java]
        viewModel.allDinings.observe(viewLifecycleOwner) { updatedDinings ->
            dinings.clear()
            dinings.addAll(updatedDinings)
            diningAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}