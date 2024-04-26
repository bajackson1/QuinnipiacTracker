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

// Adapter for building images
class BuildingImageAdapter(
    // List of building images to display
    private val buildingImages: List<Int>,
    // Click listeners for each building
    private val casClickListener: () -> Unit,
    private val cceClickListener: () -> Unit,
    private val echlinClickListener: () -> Unit,
    private val lenderClickListener: () -> Unit,
    private val libraryClickListener: () -> Unit,
    private val tatorClickListener: () -> Unit
) : RecyclerView.Adapter<BuildingImageAdapter.ViewHolder>() {

    // ViewHolder for building images
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val buildingImage: ImageView = itemView.findViewById(R.id.building_image)
    }

    // Inflate the item_building_image layout and return a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_building_image, parent, false)
        return ViewHolder(view)
    }

    // Bind the image data to the ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Set the building image resource
        holder.buildingImage.setImageResource(buildingImages[position])

        // Set the click listener for each image based on its position
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

    // Return the number of building images
    override fun getItemCount() = buildingImages.size
}