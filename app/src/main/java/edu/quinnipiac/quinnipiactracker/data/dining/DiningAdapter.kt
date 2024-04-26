/**
 * The DiningAdapter is responsible for managing the list of dining halls and
 * providing the necessary views for each building item.
 **/
package edu.quinnipiac.quinnipiactracker.data.dining

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.quinnipiac.quinnipiactracker.databinding.ItemBuildingBinding

class DiningAdapter(
    private val dinings: MutableList<Dining>,
    private val onDeleteClick: (Dining) -> Unit
) : RecyclerView.Adapter<DiningAdapter.DiningViewHolder>() {

    // Responsible for binding the data to the view
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

    // Called when adapter needs a new view holder for an item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiningViewHolder {
        val binding = ItemBuildingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiningViewHolder(binding) { dining ->
            onDeleteClick(dining)
        }
    }

    // Called when adapter needs to bind data for an item to the view holder
    override fun onBindViewHolder(holder: DiningViewHolder, position: Int) {
        val dining = dinings[position]
        holder.bind(dining)
    }

    // Returns the number of building items in the list
    override fun getItemCount() = dinings.size
}