/**
 * The ResidenceAdapter is responsible for managing the list of residence halls/dorms and
 * providing the necessary views for each building item.
 **/
package edu.quinnipiac.quinnipiactracker.data.residence

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.quinnipiac.quinnipiactracker.databinding.ItemBuildingBinding

class ResidenceAdapter(
    private val residences: MutableList<Residence>,
    private val onDeleteClick: (Residence) -> Unit
) : RecyclerView.Adapter<ResidenceAdapter.ResidenceViewHolder>() {

    // Responsible for binding the data to the view
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

    // Called when adapter needs a new view holder for an item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResidenceViewHolder {
        val binding = ItemBuildingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResidenceViewHolder(binding) { residence ->
            onDeleteClick(residence)
        }
    }

    // Called when adapter needs to bind data for an item to the view holder
    override fun onBindViewHolder(holder: ResidenceViewHolder, position: Int) {
        val residence = residences[position]
        holder.bind(residence)
    }

    // Returns the number of building items in the list
    override fun getItemCount() = residences.size
}