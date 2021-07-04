package com.crnkic.memes.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.crnkic.memes.data.model.Memes
import com.crnkic.memes.data.model.MemesContainer
import com.crnkic.memes.data.model.MemesContainerResult
import com.crnkic.memes.data.repositories.MemesContainerRepositoryImp
import java.lang.Error

class FakeMemesRepository : MemesContainerRepositoryImp {
    private var memes = mutableListOf<Memes>()

    private val observableMemesItems = MutableLiveData<List<Memes>>(memes)

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData() {
        observableMemesItems.postValue(memes)
    }

    override suspend fun insertMemesItem(meme: List<Memes>) {
        memes.addAll(meme)
        refreshLiveData()
    }

    override suspend fun deleteMemesItem(meme: List<Memes>) {
        memes.removeAll(meme)
        refreshLiveData()
    }

    override fun observeAllMemesItems(): LiveData<List<Memes>> {
        return observableMemesItems
    }

    override suspend fun fetchMemesContainer(): MemesContainerResult {
        return if (shouldReturnNetworkError) {
            MemesContainerResult.Failure(Error("Error", null))
        } else {
            MemesContainerResult.Success(listOf(Memes("1", "meme", "url", 1, 1, 1)))
        }
    }
}