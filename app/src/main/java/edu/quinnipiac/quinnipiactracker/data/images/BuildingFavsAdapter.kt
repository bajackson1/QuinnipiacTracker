/**
 * This is how you can get a scrollable gallery like we have in Info and Favs.
 * It allows us to display a large array of images in a smooth fashion.
 */
package edu.quinnipiac.quinnipiactracker.data.images

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import edu.quinnipiac.quinnipiactracker.R

class BuildingFavsAdapter(
    private val buildingImages: List<Int>,
    private val casClickListener: () -> Unit,
    private val cceClickListener: () -> Unit,
    private val echlinClickListener: () -> Unit,
    private val lenderClickListener: () -> Unit,
    private val libraryClickListener: () -> Unit,
    private val tatorClickListener: () -> Unit
) : RecyclerView.Adapter<BuildingFavsAdapter.ViewHolder>() {
    private val favoriteItems = mutableListOf<Int>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val buildingImage: ImageView = itemView.findViewById(R.id.building_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_building_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.buildingImage.setImageResource(buildingImages[position])
        holder.buildingImage.visibility = if (favoriteItems.contains(buildingImages[position])) View.VISIBLE else View.INVISIBLE

        holder.buildingImage.setOnClickListener {
            when (position) {
                0 -> casClickListener()
                1 -> cceClickListener()
                2 -> echlinClickListener()
                3 -> lenderClickListener()
                4 -> libraryClickListener()
                5 -> tatorClickListener()
            }
        }
    }

    override fun getItemCount() = buildingImages.size
}