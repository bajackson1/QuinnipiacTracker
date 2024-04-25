package edu.quinnipiac.quinnipiactracker.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.quinnipiac.quinnipiactracker.databinding.ItemBuildingBinding

class ResidenceAdapter(
    private val residences: MutableList<Residence>,
    private val onDeleteClick: (Residence) -> Unit
) : RecyclerView.Adapter<ResidenceAdapter.ResidenceViewHolder>() {

    class ResidenceViewHolder(
        private val binding: ItemBuildingBinding,
        private val onDeleteClick: (Residence) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(residence: Residence) {
            binding.buildingTitle.text = residence.itemName
            binding.deleteIcon.setOnClickListener {
                onDeleteClick(residence)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResidenceViewHolder {
        val binding = ItemBuildingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResidenceViewHolder(binding) { residence ->
            onDeleteClick(residence)
        }
    }

    override fun onBindViewHolder(holder: ResidenceViewHolder, position: Int) {
        val residence = residences[position]
        holder.bind(residence)
    }

    override fun getItemCount() = residences.size
}