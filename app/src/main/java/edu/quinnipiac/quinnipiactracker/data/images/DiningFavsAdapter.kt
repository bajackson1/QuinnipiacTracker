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
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_building_image, parent, false)
        return DiningFavsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiningFavsViewHolder, position: Int) {
        val (imageId, diningImage) = diningFavs[position]
        holder.bind(diningImage)
    }

    override fun getItemCount(): Int {
        return diningFavs.size
    }

    inner class DiningFavsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.building_image)

        init {
            itemView.setOnClickListener {
                clickListeners[diningFavs[adapterPosition].first]?.invoke()
            }
        }

        fun bind(diningImage: DiningImage) {
            Glide.with(itemView.context)
                .load(diningImage.id)
                .into(imageView)
        }
    }
}