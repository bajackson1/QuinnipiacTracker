package edu.quinnipiac.quinnipiactracker.data.academic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BuildingViewModel(private val buildingDao: BuildingDao) : ViewModel() {
    val allBuildings = buildingDao.getItems().asLiveData()

    fun addNewBuilding(building: Building) {
        viewModelScope.launch {
            buildingDao.insert(building)
        }
    }

    fun deleteBuilding(building: Building) {
        viewModelScope.launch {
            buildingDao.delete(building)
        }
    }
}

class BuildingViewModelFactory(private val buildingDao: BuildingDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BuildingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BuildingViewModel(buildingDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}