package com.crnkic.memes.data.database

import com.crnkic.memes.data.model.MemesContainer
import com.crnkic.memes.data.model.MemesContainerResult

interface MemesLocalDataSourceImplementation {
    suspend fun getSavedMemesContainer():MemesContainerResult
    suspend fun insert(memesContainer:MemesContainer)
    suspend fun deleteAll()
}