/**
 * The DiningViewModel is a ViewModel that provides data and functionality related to the
 * Dining entities.
 */
package edu.quinnipiac.quinnipiactracker.data.dining

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DiningViewModel(private val diningDao: DiningDao) : ViewModel() {
    val allDinings = diningDao.getItems().asLiveData()

    // Used to insert a new Dining into the database
    fun addNewDining(dining: Dining) {
        viewModelScope.launch {
            diningDao.insert(dining)
        }
    }

    // Used to delete a Dining from the database
    fun deleteDining(dining: Dining) {
        viewModelScope.launch {
            diningDao.delete(dining)
        }
    }
}

// ViewModelProvider.Factory that creates instances of the DiningViewModel class
class DiningViewModelFactory(private val diningDao: DiningDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DiningViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DiningViewModel(diningDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}