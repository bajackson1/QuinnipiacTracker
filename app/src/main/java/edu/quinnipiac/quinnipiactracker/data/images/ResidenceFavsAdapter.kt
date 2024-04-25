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

class ResidenceFavsAdapter(
    // List of images to be displayed
    private val residenceFavs: List<Int>,
    // Click listeners for each building
    private val commonsClickListener: () -> Unit,
    private val complexClickListener: () -> Unit,
    private val danaClickListener: () -> Unit,
    private val hillClickListener: () -> Unit,
    private val irmaClickListener: () -> Unit,
    private val larsonClickListener: () -> Unit,
    private val ledgesClickListener: () -> Unit,
    private val mountainviewClickListener: () -> Unit,
    private val perlClickListener: () -> Unit,
    private val troupClickListener: () -> Unit,
    private val villageClickListener: () -> Unit,
) : RecyclerView.Adapter<ResidenceFavsAdapter.ViewHolder>() {
    private val favoriteItems = mutableListOf<Int>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val residenceImage: ImageView = itemView.findViewById(R.id.building_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_building_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.residenceImage.setImageResource(residenceFavs[position])
        holder.residenceImage.visibility = if (favoriteItems.contains(residenceFavs[position])) View.VISIBLE else View.INVISIBLE

        // Setting the click listener for each image based on its position in the view
        holder.residenceImage.setOnClickListener {
            when (position) {
                0 -> commonsClickListener()
                1 -> complexClickListener()
                2 -> danaClickListener()
                3 -> hillClickListener()
                4 -> irmaClickListener()
                5 -> larsonClickListener()
                6 -> ledgesClickListener()
                7 -> mountainviewClickListener()
                8 -> perlClickListener()
                9 -> troupClickListener()
                10 -> villageClickListener()
            }
        }
    }

    override fun getItemCount() = residenceFavs.size

    fun updateFavoriteItems(newFavoriteItems: List<Int>) {
        favoriteItems.clear()
        favoriteItems.addAll(newFavoriteItems)
        notifyDataSetChanged()
    }
}