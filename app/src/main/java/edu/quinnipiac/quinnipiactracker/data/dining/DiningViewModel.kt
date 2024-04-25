package edu.quinnipiac.quinnipiactracker.data.dining

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DiningViewModel(private val diningDao: DiningDao) : ViewModel() {
    val allDinings = diningDao.getItems().asLiveData()

    fun addNewDining(dining: Dining) {
        viewModelScope.launch {
            diningDao.insert(dining)
        }
    }

    fun deleteDining(dining: Dining) {
        viewModelScope.launch {
            diningDao.delete(dining)
        }
    }
}

class DiningViewModelFactory(private val diningDao: DiningDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DiningViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DiningViewModel(diningDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}