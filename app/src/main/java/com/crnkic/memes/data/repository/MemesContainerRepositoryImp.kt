package com.crnkic.memes.data.repository

import com.crnkic.memes.data.model.MemesContainerResult

interface MemesContainerRepositoryImp {
    suspend fun fetchMemesContainer(): MemesContainerResult
}