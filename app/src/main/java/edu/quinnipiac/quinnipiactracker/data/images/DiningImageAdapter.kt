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

class DiningImageAdapter(
    // List of images to be displayed
    private val diningImages: List<Int>,
    // Click listeners for each building
    private val studentCenterClickListener: () -> Unit,
    private val ratClickListener: () -> Unit
) : RecyclerView.Adapter<DiningImageAdapter.ViewHolder>() {

    // Represents a singular image in the view gallery
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val diningImage: ImageView = itemView.findViewById(R.id.building_image)
    }

    // Called when the adapter creates a new ViewHolder
    // Inflates from the item_building_image.xml file
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_building_image, parent, false)
        return ViewHolder(view)
    }

    // Binding the image data to the view holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Set the dining image resource
        holder.diningImage.setImageResource(diningImages[position])

        // Set the click listener for each image based on its position in the view
        holder.diningImage.setOnClickListener {
            when (position) {
                0 -> studentCenterClickListener()
                1 -> ratClickListener()
            }
        }
    }

    // Returns the number of items in the view gallery
    override fun getItemCount() = diningImages.size
}