package com.crnkic.memes.data.repository

import androidx.lifecycle.LiveData
import com.crnkic.memes.data.localdb.MemesContainerDao
import com.crnkic.memes.data.model.Memes
import com.crnkic.memes.data.model.MemesContainerResult
import com.crnkic.memes.data.network.GetDataService
import java.lang.Error
import javax.inject.Inject

class MemesContainerRepository @Inject constructor(
    private val memesContainerDao: MemesContainerDao,
    private val getDataService: GetDataService) : MemesContainerRepositoryImp{

    override suspend fun insertMemesItem(meme: Memes) {
        memesContainerDao.insertMemeItem(meme)
    }

    override suspend fun deleteMemesItem(meme: Memes) {
      memesContainerDao.deleteMemeItem(meme)
    }

    override fun observeAllMemesItems(): LiveData<List<Memes>> {
       return memesContainerDao.observeAllMemesItems()
    }

    override suspend fun fetchMemesContainer(): MemesContainerResult {
        return try {
            val response = getDataService.getMemesData()
            if(response.isSuccessful) {
                response.body()?.let {
                    return@let MemesContainerResult.Success(it)
                } ?: MemesContainerResult.Failure(Error("An unknown error occured", null))
            } else {
                MemesContainerResult.Failure(Error("An unknown error occured", null))
            }
        } catch (e: Exception) {
            MemesContainerResult.Failure(Error("Couldn't reach the server. Check your internet connection", null))
        }
    }
}