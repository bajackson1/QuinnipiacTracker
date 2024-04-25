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

class DiningFavsAdapter(
    private val diningFavs: List<Int>,
    private val studentCenterClickListener: () -> Unit,
    private val ratClickListener: () -> Unit
) : RecyclerView.Adapter<DiningFavsAdapter.ViewHolder>() {
    private val favoriteItems = mutableListOf<Int>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val diningImage: ImageView = itemView.findViewById(R.id.building_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_building_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.diningImage.setImageResource(diningFavs[position])
        holder.diningImage.visibility = if (favoriteItems.contains(diningFavs[position])) View.VISIBLE else View.INVISIBLE

        holder.diningImage.setOnClickListener {
            when (position) {
                0 -> studentCenterClickListener()
                1 -> ratClickListener()
            }
        }
    }

    override fun getItemCount() = diningFavs.size

    fun updateFavoriteItems(newFavoriteItems: List<Int>) {
        favoriteItems.clear()
        favoriteItems.addAll(newFavoriteItems)
        notifyDataSetChanged()
    }
}