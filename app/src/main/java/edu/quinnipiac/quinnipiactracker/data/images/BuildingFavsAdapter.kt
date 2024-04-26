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
import com.bumptech.glide.Glide
import edu.quinnipiac.quinnipiactracker.R

// Adapter for building favorites
class BuildingFavsAdapter(
    private val buildingFavs: List<Pair<Int, BuildingImage>>,
    private val clickListeners: Map<Int, () -> Unit>
) : RecyclerView.Adapter<BuildingFavsAdapter.BuildingFavsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingFavsViewHolder {
        // Inflate the item_building_image layout and return a new ViewHolder
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_building_image, parent, false)
        return BuildingFavsViewHolder(view)
    }

    override fun onBindViewHolder(holder: BuildingFavsViewHolder, position: Int) {
        // Get the current building image and its ID
        val (imageId, buildingImage) = buildingFavs[position]
        // Bind the image data to the ViewHolder
        holder.bind(buildingImage)
    }

    override fun getItemCount(): Int {
        // Return the number of building favorites
        return buildingFavs.size
    }

    // ViewHolder for building images
    inner class BuildingFavsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.building_image)

        init {
            // Set an onClickListener for the image view
            itemView.setOnClickListener {
                // Get the click listener for the current building ID and invoke it
                clickListeners[buildingFavs[adapterPosition].first]?.invoke()
            }
        }

        // Bind the building image data to the ViewHolder
        fun bind(buildingImage: BuildingImage) {
            // Load the image using Glide
            Glide.with(itemView.context)
                .load(buildingImage.id)
                .into(imageView)
        }
    }
}