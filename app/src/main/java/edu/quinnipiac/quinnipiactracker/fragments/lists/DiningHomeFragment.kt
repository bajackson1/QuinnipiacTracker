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
            findNavController().navigate(R.id.action_diningHomeFragment_to_homeFragment)
        }

        val viewModel = ViewModelProvider(this, DiningViewModelFactory(DiningRoomDatabase.getDatabase(requireContext()).diningDao()))[DiningViewModel::class.java]
        viewModel.allDinings.observe(viewLifecycleOwner) { dinings ->
            this.dinings.clear()
            this.dinings.addAll(dinings)
            diningAdapter.notifyDataSetChanged()
            updateUI(dinings)
        }
    }

    private fun updateUI(dinings: List<Dining>) {
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