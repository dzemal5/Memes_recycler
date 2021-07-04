package com.crnkic.memes.ui.memeslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crnkic.memes.data.model.Memes
import com.crnkic.memes.data.model.MemesContainerResult
import com.crnkic.memes.data.repositories.MemesContainerRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MemesListViewModel @Inject constructor(
    private var repository: MemesContainerRepositoryImp
) : ViewModel() {

    private var _memesContainerFetchResultLiveData = MutableLiveData<MemesContainerResult>()
    val memesContainerFetchResultLiveData: LiveData<MemesContainerResult>
        get() = _memesContainerFetchResultLiveData

    private var _memesContainerSavedResultLiveData = MutableLiveData<List<Memes>>()
    val memesContainerSavedResultLiveData: LiveData<List<Memes>>
        get() = _memesContainerSavedResultLiveData



    init {
        Timber.d("fetching data")
        fetchMemesContainer()
    }

    val memesItems = repository.observeAllMemesItems()

    private fun insertMemesIntoDb(meme: List<Memes>) = viewModelScope.launch {
        repository.insertMemesItem(meme)
    }

    fun fetchMemesContainer() {
        _memesContainerFetchResultLiveData.value = MemesContainerResult.IsLoading
        viewModelScope.launch {
            val fetchResult = repository.fetchMemesContainer()
            _memesContainerFetchResultLiveData.value = fetchResult
            if (fetchResult is MemesContainerResult.Success) {
                insertMemesIntoDb(fetchResult.memes)
            }
        }

    }

    fun getSavedMemesContainer() {
        _memesContainerSavedResultLiveData.value = repository.observeAllMemesItems().value
    }

}