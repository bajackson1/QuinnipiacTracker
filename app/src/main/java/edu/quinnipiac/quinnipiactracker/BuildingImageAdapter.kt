/**
 * This is how you can get a scrollable gallery like we have in Info and Favs.
 * It allows us to display a large array of images in a smooth fashion.
 */
package edu.quinnipiac.quinnipiactracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class BuildingImageAdapter(
    // List of images to be displayed
    private val buildingImages: List<Int>,
    // Click listeners for each building
    private val casClickListener: () -> Unit,
    private val cceClickListener: () -> Unit,
    private val echlinClickListener: () -> Unit,
    private val lenderClickListener: () -> Unit,
    private val libraryClickListener: () -> Unit,
    private val tatorClickListener: () -> Unit
) : RecyclerView.Adapter<BuildingImageAdapter.ViewHolder>() {

    // Represents a singular image in the view gallery
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val buildingImage: ImageView = itemView.findViewById(R.id.building_image)
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
        holder.buildingImage.setImageResource(buildingImages[position])

        // Setting the click listener for each image based on its position in the view
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

    // Returns the number of items in the view gallery
    override fun getItemCount() = buildingImages.size
}