package com.crnkic.memes.ui.memeslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crnkic.memes.data.model.Memes
import com.crnkic.memes.data.model.MemesContainerResult
import com.crnkic.memes.data.repositories.MemesContainerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemesListViewModel @Inject constructor(
    private var repository: MemesContainerRepository
) : ViewModel() {

    init {
        Log.d("Fetch_Data", "fetching data")

        fetchMemesContainer()
    }

    val memesItems = repository.observeAllMemesItems()

    private val _memesContainerResultLiveData = MutableLiveData<MemesContainerResult>()
    val memesContainerResultLiveData: LiveData<MemesContainerResult>
        get() = _memesContainerResultLiveData

    fun insertMemesIntoDb(meme: Memes) = viewModelScope.launch {
        repository.insertMemesItem(meme)
    }

    fun fetchMemesContainer() {

        _memesContainerResultLiveData.value = MemesContainerResult.IsLoading
        viewModelScope.launch {
            _memesContainerResultLiveData.value = repository.fetchMemesContainer()
        }
    }


//    private val _memesContainerResultLiveData = MutableLiveData<MemesContainerResult>()
//    val memesContainerResultLiveData: LiveData<MemesContainerResult>
//        get() = _memesContainerResultLiveData
//
//    init {
//        Log.d("LOG_LOG", "before result is success" )
//
//        fetchMemesContainer()
//    }
//
//    fun fetchMemesContainer() {
//        _memesContainerResultLiveData.value = MemesContainerResult.IsLoading
//        viewModelScope.launch {
//            _memesContainerResultLiveData.postValue(memesContainerRepository.fetchMemesContainer())
//        }
//    }
}