package edu.quinnipiac.quinnipiactracker.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.quinnipiac.quinnipiactracker.databinding.ItemBuildingBinding

class BuildingAdapter(
    private val buildings: MutableList<Building>,
    private val onDeleteClick: (Building) -> Unit
) : RecyclerView.Adapter<BuildingAdapter.BuildingViewHolder>() {

    class BuildingViewHolder(
        private val binding: ItemBuildingBinding,
        private val onDeleteClick: (Building) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(building: Building) {
            binding.buildingTitle.text = building.itemName
            binding.deleteIcon.setOnClickListener {
                onDeleteClick(building)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingViewHolder {
        val binding = ItemBuildingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuildingViewHolder(binding) { building ->
            onDeleteClick(building)
        }
    }

    override fun onBindViewHolder(holder: BuildingViewHolder, position: Int) {
        val building = buildings[position]
        holder.bind(building)
    }

    override fun getItemCount() = buildings.size
}