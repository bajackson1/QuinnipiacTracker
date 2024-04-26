/**
 * The BuildingAdapter is responsible for managing the list of academic buildings and
 * providing the necessary views for each building item.
**/
package edu.quinnipiac.quinnipiactracker.data.academic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.quinnipiac.quinnipiactracker.databinding.ItemBuildingBinding

class BuildingAdapter(
    private val buildings: MutableList<Building>,
    private val onDeleteClick: (Building) -> Unit
) : RecyclerView.Adapter<BuildingAdapter.BuildingViewHolder>() {

    // Responsible for binding the data to the view
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

    // Called when adapter needs a new view holder for an item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingViewHolder {
        val binding = ItemBuildingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuildingViewHolder(binding) { building ->
            onDeleteClick(building)
        }
    }

    // Called when adapter needs to bind data for an item to the view holder
    override fun onBindViewHolder(holder: BuildingViewHolder, position: Int) {
        val building = buildings[position]
        holder.bind(building)
    }

    // Returns the number of building items in the list
    override fun getItemCount() = buildings.size
}