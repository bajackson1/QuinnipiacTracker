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

class DiningFavsAdapter(
    private val diningFavs: List<Pair<Int, DiningImage>>,
    private val clickListeners: Map<Int, () -> Unit>
) : RecyclerView.Adapter<DiningFavsAdapter.DiningFavsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiningFavsViewHolder {
        // Inflate the item_building_image layout and return a new ViewHolder
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_building_image, parent, false)
        return DiningFavsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiningFavsViewHolder, position: Int) {
        // Get the current dining image and its ID
        val (imageId, diningImage) = diningFavs[position]
        // Bind the image data to the ViewHolder
        holder.bind(diningImage)
    }

    override fun getItemCount(): Int {
        // Return the number of dining favorites
        return diningFavs.size
    }

    inner class DiningFavsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.building_image)

        init {
            // Set an onClickListener for the image view
            itemView.setOnClickListener {
                // Get the click listener for the current dining ID and invoke it
                clickListeners[diningFavs[adapterPosition].first]?.invoke()
            }
        }

        // Bind the dining image data to the ViewHolder
        fun bind(diningImage: DiningImage) {
            // Load the image using Glide
            Glide.with(itemView.context)
                .load(diningImage.id)
                .into(imageView)
        }
    }
}