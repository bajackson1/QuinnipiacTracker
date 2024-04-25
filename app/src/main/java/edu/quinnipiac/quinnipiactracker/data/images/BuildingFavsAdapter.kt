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

class BuildingFavsAdapter(
    private val buildingFavs: List<Pair<Int, BuildingImage>>,
    private val clickListeners: Map<Int, () -> Unit>
) : RecyclerView.Adapter<BuildingFavsAdapter.BuildingFavsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingFavsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_building_image, parent, false)
        return BuildingFavsViewHolder(view)
    }

    override fun onBindViewHolder(holder: BuildingFavsViewHolder, position: Int) {
        val (imageId, buildingImage) = buildingFavs[position]
        holder.bind(buildingImage)
    }

    override fun getItemCount(): Int {
        return buildingFavs.size
    }

    inner class BuildingFavsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.building_image)

        init {
            itemView.setOnClickListener {
                clickListeners[buildingFavs[adapterPosition].first]?.invoke()
            }
        }

        fun bind(buildingImage: BuildingImage) {
            Glide.with(itemView.context)
                .load(buildingImage.id)
                .into(imageView)
        }
    }
}