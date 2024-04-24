package edu.quinnipiac.quinnipiactracker.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.quinnipiac.quinnipiactracker.databinding.ItemBuildingBinding

class BuildingAdapter(
    private val buildings: List<Building>
) : RecyclerView.Adapter<BuildingAdapter.BuildingViewHolder>() {

    class BuildingViewHolder(private val binding: ItemBuildingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(building: Building) {
            binding.noteTitle.text = building.itemName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingViewHolder {
        val binding = ItemBuildingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuildingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuildingViewHolder, position: Int) {
        val building = buildings[position]
        holder.bind(building)
    }

    override fun getItemCount() = buildings.size
}