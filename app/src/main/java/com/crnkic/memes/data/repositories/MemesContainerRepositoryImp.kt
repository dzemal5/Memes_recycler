package com.crnkic.memes.data.repositories

import androidx.lifecycle.LiveData
import com.crnkic.memes.data.model.Memes
import com.crnkic.memes.data.model.MemesContainer
import com.crnkic.memes.data.model.MemesContainerResult

interface MemesContainerRepositoryImp {
    suspend fun insertMemesItem(meme: Memes)

    suspend fun deleteMemesItem(meme: Memes)

    fun observeAllMemesItems(): LiveData<List<Memes>>

    suspend fun fetchMemesContainer() : MemesContainerResult
}