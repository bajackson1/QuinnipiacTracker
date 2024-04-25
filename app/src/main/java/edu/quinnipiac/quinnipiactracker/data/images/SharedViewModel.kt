package edu.quinnipiac.quinnipiactracker.data.images

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _favoriteItems = MutableLiveData<List<Int>>(emptyList())
    val favoriteItems: LiveData<List<Int>> = _favoriteItems

    fun addFavoriteItem(itemId: Int) {
        val currentList = _favoriteItems.value?.toMutableList() ?: mutableListOf()
        currentList.add(itemId)
        _favoriteItems.value = currentList
    }
}