/**
* This is a shared view model for managing favorite images for all three building types.
*/
package edu.quinnipiac.quinnipiactracker.data.images

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    // List of academic building favorites with data
    private val _buildingFavs = mutableListOf<Pair<Int, BuildingImage>>()
    val buildingFavs: List<Pair<Int, BuildingImage>> = _buildingFavs
    private val _buildingFavsCount = MutableLiveData<Int>()
    val buildingFavsCount: LiveData<Int> = _buildingFavsCount

    // List of dining hall favorites with data
    private val _diningFavs = mutableListOf<Pair<Int, DiningImage>>()
    val diningFavs: List<Pair<Int, DiningImage>> = _diningFavs
    private val _diningFavsCount = MutableLiveData<Int>()
    val diningFavsCount: LiveData<Int> = _diningFavsCount

    // List of residence hall/dorm favorites with data
    private val _residenceFavs = mutableListOf<Pair<Int, ResidenceImage>>()
    val residenceFavs: List<Pair<Int, ResidenceImage>> = _residenceFavs
    private val _residenceFavsCount = MutableLiveData<Int>()
    val residenceFavsCount: LiveData<Int> = _residenceFavsCount

    // Sets to keep track of unique IDs for each type of favorite
    private val _uniqueBuildingIds = mutableSetOf<Int>()
    private val _uniqueDiningIds = mutableSetOf<Int>()
    private val _uniqueResidenceIds = mutableSetOf<Int>()

    // Method to add a building favorite
    fun addBuildingFav(image: BuildingImage) {
        if (_uniqueBuildingIds.add(image.id)) {
            _buildingFavs.add(Pair(image.id, image))
            _buildingFavsCount.value = _buildingFavs.size
        }
    }

    // Method to add a dining favorite
    fun addDiningFav(image: DiningImage) {
        if (_uniqueDiningIds.add(image.id)) {
            _diningFavs.add(Pair(image.id, image))
            _diningFavsCount.value = _diningFavs.size
        }
    }

    // Method to add a residence favorite
    fun addResidenceFav(image: ResidenceImage) {
        if (_uniqueResidenceIds.add(image.id)) {
            _residenceFavs.add(Pair(image.id, image))
            _residenceFavsCount.value = _residenceFavs.size
        }
    }

    // Method to check if a building is a favorite
    fun isBuildingFav(image: BuildingImage): Boolean {
        return _uniqueBuildingIds.contains(image.id)
    }

    // Method to remove a building favorite
    fun removeBuildingFav(image: BuildingImage) {
        if (_uniqueBuildingIds.remove(image.id)) {
            _buildingFavs.removeAll { it.second.id == image.id }
            _buildingFavsCount.value = _buildingFavs.size
        }
    }

    // Method to check if a dining hall is a favorite
    fun isDiningFav(image: DiningImage): Boolean {
        return _uniqueDiningIds.contains(image.id)
    }

    // Method to remove a dining hall favorite
    fun removeDiningFav(image: DiningImage) {
        if (_uniqueDiningIds.remove(image.id)) {
            _diningFavs.removeAll { it.second.id == image.id }
            _diningFavsCount.value = _diningFavs.size
        }
    }

    // Method to check if a residence hall is a favorite
    fun isResidenceFav(image: ResidenceImage): Boolean {
        return _uniqueResidenceIds.contains(image.id)
    }

    // Method to remove a residence hall favorite
    fun removeResidenceFav(image: ResidenceImage) {
        if (_uniqueResidenceIds.remove(image.id)) {
            _residenceFavs.removeAll { it.second.id == image.id }
            _residenceFavsCount.value = _residenceFavs.size
        }
    }
}