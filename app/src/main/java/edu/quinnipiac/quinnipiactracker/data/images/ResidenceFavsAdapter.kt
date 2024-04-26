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

class ResidenceFavsAdapter(
    private val residenceFavs: List<Pair<Int, ResidenceImage>>,
    private val clickListeners: Map<Int, () -> Unit>
) : RecyclerView.Adapter<ResidenceFavsAdapter.ResidenceFavsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResidenceFavsViewHolder {
        // Inflate the item_building_image layout and return a new ViewHolder
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_building_image, parent, false)
        return ResidenceFavsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResidenceFavsViewHolder, position: Int) {
        // Get the current residence image and its ID
        val (imageId, residenceImage) = residenceFavs[position]
        // Bind the image data to the ViewHolder
        holder.bind(residenceImage)
    }

    override fun getItemCount(): Int {
        // Return the number of residence favorites
        return residenceFavs.size
    }

    inner class ResidenceFavsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.building_image)

        init {
            // Set an onClickListener for the image view
            itemView.setOnClickListener {
                // Get the click listener for the current residence ID and invoke it
                clickListeners[residenceFavs[adapterPosition].first]?.invoke()
            }
        }

        // Bind the residence image data to the ViewHolder
        fun bind(residenceImage: ResidenceImage) {
            // Load the image using Glide
            Glide.with(itemView.context)
                .load(residenceImage.id)
                .into(imageView)
        }
    }
}