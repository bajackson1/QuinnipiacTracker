package edu.quinnipiac.quinnipiactracker.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.quinnipiac.quinnipiactracker.databinding.ItemBuildingBinding

class DiningAdapter(
    private val dinings: MutableList<Dining>,
    private val onDeleteClick: (Dining) -> Unit
) : RecyclerView.Adapter<DiningAdapter.DiningViewHolder>() {

    class DiningViewHolder(
        private val binding: ItemBuildingBinding,
        private val onDeleteClick: (Dining) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dining: Dining) {
            binding.buildingTitle.text = dining.itemName
            binding.deleteIcon.setOnClickListener {
                onDeleteClick(dining)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiningViewHolder {
        val binding = ItemBuildingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiningViewHolder(binding) { dining ->
            onDeleteClick(dining)
        }
    }

    override fun onBindViewHolder(holder: DiningViewHolder, position: Int) {
        val dining = dinings[position]
        holder.bind(dining)
    }

    override fun getItemCount() = dinings.size
}