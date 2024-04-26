/**
 * The BuildingViewModel is a ViewModel that provides data and functionality related to the
 * Building entities.
 */
package edu.quinnipiac.quinnipiactracker.data.academic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BuildingViewModel(private val buildingDao: BuildingDao) : ViewModel() {
    val allBuildings = buildingDao.getItems().asLiveData()

    // Used to insert a new Building into the database
    fun addNewBuilding(building: Building) {
        viewModelScope.launch {
            buildingDao.insert(building)
        }
    }

    // Used to delete a Building from the database
    fun deleteBuilding(building: Building) {
        viewModelScope.launch {
            buildingDao.delete(building)
        }
    }
}

// ViewModelProvider.Factory that creates instances of the BuildingViewModel class
class BuildingViewModelFactory(private val buildingDao: BuildingDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BuildingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BuildingViewModel(buildingDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}