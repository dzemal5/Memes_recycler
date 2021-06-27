package com.crnkic.memes.data.repository

import com.crnkic.memes.data.model.MemesContainer
import com.crnkic.memes.data.model.MemesContainerResult

interface MemesContainerRepositoryImp {
    suspend fun fetchMemesContainer(): MemesContainerResult
    suspend fun getSavedMemesContainer() : MemesContainerResult
    suspend fun insertToDatabase(memesContainer : MemesContainer)
}