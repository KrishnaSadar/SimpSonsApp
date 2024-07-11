package com.example.mlsc_task.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mlsc_task.core.RetrofitClient
import com.example.mlsc_task.models.chareCter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {

    private var _charactersList = MutableLiveData<List<chareCter>>()
    val charactersList: LiveData<List<chareCter>> get() = _charactersList

    fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.getCharacters()
            withContext(Dispatchers.Main) {
                Log.d("API", response.body().toString())
                _charactersList.value = response.body()
            }
        }
    }

    fun getCharacter(character: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.getCharacter(character)
            withContext(Dispatchers.Main) {
                Log.d("API", response.body().toString())
                _charactersList.value = response.body()
            }
        }
    }

}
