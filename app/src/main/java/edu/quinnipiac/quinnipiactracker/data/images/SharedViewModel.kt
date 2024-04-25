package edu.quinnipiac.quinnipiactracker.data.images

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _buildingFavs = mutableListOf<Pair<Int, BuildingImage>>()
    val buildingFavs: List<Pair<Int, BuildingImage>> = _buildingFavs
    private val _buildingFavsCount = MutableLiveData<Int>()
    val buildingFavsCount: LiveData<Int> = _buildingFavsCount

    private val _diningFavs = mutableListOf<Pair<Int, DiningImage>>()
    val diningFavs: List<Pair<Int, DiningImage>> = _diningFavs
    private val _diningFavsCount = MutableLiveData<Int>()
    val diningFavsCount: LiveData<Int> = _diningFavsCount

    private val _residenceFavs = mutableListOf<Pair<Int, ResidenceImage>>()
    val residenceFavs: List<Pair<Int, ResidenceImage>> = _residenceFavs
    private val _residenceFavsCount = MutableLiveData<Int>()
    val residenceFavsCount: LiveData<Int> = _residenceFavsCount

    private val _uniqueBuildingIds = mutableSetOf<Int>()
    private val _uniqueDiningIds = mutableSetOf<Int>()
    private val _uniqueResidenceIds = mutableSetOf<Int>()

    // Add methods to manage the favorite images
    fun addBuildingFav(image: BuildingImage) {
        if (_uniqueBuildingIds.add(image.id)) {
            _buildingFavs.add(Pair(image.id, image))
            _buildingFavsCount.value = _buildingFavs.size
        }
    }

    fun addDiningFav(image: DiningImage) {
        if (_uniqueDiningIds.add(image.id)) {
            _diningFavs.add(Pair(image.id, image))
            _diningFavsCount.value = _diningFavs.size
        }
    }

    fun addResidenceFav(image: ResidenceImage) {
        if (_uniqueResidenceIds.add(image.id)) {
            _residenceFavs.add(Pair(image.id, image))
            _residenceFavsCount.value = _residenceFavs.size
        }
    }

    // Add methods to remove favorite images
    fun removeBuildingFav(image: BuildingImage) {
        _uniqueBuildingIds.remove(image.id)
        _buildingFavs.removeAll { it.second == image }
        _buildingFavsCount.value = _buildingFavs.size
    }

    fun removeDiningFav(image: DiningImage) {
        _uniqueDiningIds.remove(image.id)
        _diningFavs.removeAll { it.second == image }
        _diningFavsCount.value = _diningFavs.size
    }

    fun removeResidenceFav(image: ResidenceImage) {
        _uniqueResidenceIds.remove(image.id)
        _residenceFavs.removeAll { it.second == image }
        _residenceFavsCount.value = _residenceFavs.size
    }
}