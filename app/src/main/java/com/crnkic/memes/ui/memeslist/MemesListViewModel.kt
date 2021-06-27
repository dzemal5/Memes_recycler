package com.crnkic.memes.ui.memeslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crnkic.memes.data.model.MemesContainerResult
import com.crnkic.memes.data.repository.MemesContainerRepository
import com.crnkic.memes.data.repository.MemesContainerRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemesListViewModel @Inject constructor(private var memesContainerRepository: MemesContainerRepository) : ViewModel() {

    private val _memesContainerResultLiveData = MutableLiveData<MemesContainerResult>()
    val memesContainerResultLiveData: LiveData<MemesContainerResult>
        get() = _memesContainerResultLiveData

    init {
        fetchMemesContainer()
    }

    fun fetchMemesContainer() {
        _memesContainerResultLiveData.value = MemesContainerResult.IsLoading
        viewModelScope.launch {
            _memesContainerResultLiveData.value = memesContainerRepository.fetchMemesContainer()
        }
    }

}