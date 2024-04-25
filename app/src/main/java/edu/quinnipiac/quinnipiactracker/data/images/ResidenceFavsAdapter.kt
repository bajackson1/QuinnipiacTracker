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
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_building_image, parent, false)
        return ResidenceFavsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResidenceFavsViewHolder, position: Int) {
        val (imageId, residenceImage) = residenceFavs[position]
        holder.bind(residenceImage)
    }

    override fun getItemCount(): Int {
        return residenceFavs.size
    }

    inner class ResidenceFavsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.building_image)

        init {
            itemView.setOnClickListener {
                clickListeners[residenceFavs[adapterPosition].first]?.invoke()
            }
        }

        fun bind(residenceImage: ResidenceImage) {
            Glide.with(itemView.context)
                .load(residenceImage.id)
                .into(imageView)
        }
    }
}