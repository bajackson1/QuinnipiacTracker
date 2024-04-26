/**
 * The ResidenceViewModel is a ViewModel that provides data and functionality related to the
 * Residence entities.
 */
package edu.quinnipiac.quinnipiactracker.data.residence

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ResidenceViewModel(private val residenceDao: ResidenceDao) : ViewModel() {
    val allResidences = residenceDao.getItems().asLiveData()

    // Used to insert a new Residence into the database
    fun addNewResidence(residence: Residence) {
        viewModelScope.launch {
            residenceDao.insert(residence)
        }
    }

    // Used to delete a Residence from the database
    fun deleteResidence(residence: Residence) {
        viewModelScope.launch {
            residenceDao.delete(residence)
        }
    }
}

// ViewModelProvider.Factory that creates instances of the ResidenceViewModel class
class ResidenceViewModelFactory(private val residenceDao: ResidenceDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResidenceViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ResidenceViewModel(residenceDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}