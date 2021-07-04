package com.crnkic.memes.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.crnkic.memes.data.localdb.MemesContainerDao
import com.crnkic.memes.data.model.Memes
import com.crnkic.memes.data.model.MemesContainer
import com.crnkic.memes.data.model.MemesContainerResult
import com.crnkic.memes.data.network.GetDataService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import java.lang.Error
import javax.inject.Inject
import javax.security.auth.callback.Callback

class MemesContainerRepository @Inject constructor(
    private val memesContainerDao: MemesContainerDao,
    private val getDataService: GetDataService
) : MemesContainerRepositoryImp {

    override suspend fun insertMemesItem(meme: Memes) {
        memesContainerDao.insertMemeItem(meme)
    }

    override suspend fun deleteMemesItem(meme: Memes) {
        memesContainerDao.deleteMemeItem(meme)
    }

    override fun observeAllMemesItems(): LiveData<List<Memes>> {
        return memesContainerDao.observeAllMemesItems()
    }

    override suspend fun fetchMemesContainer(): MemesContainerResult =
        withContext(Dispatchers.IO) {
            val memesCall = getDataService.getMemesData()
            try {
                val response = memesCall.execute()
                val memes = response.body()

                memes?.let {
                    return@withContext MemesContainerResult.Success(it.data.memes)
                } ?: run {
                    return@withContext MemesContainerResult.Failure(
                        Error("An unknown error occured")
                    )
                }
            } catch (e: Exception) {
                return@withContext MemesContainerResult.Failure(
                    Error(
                        "Couldn't reach the server. Check your internet connection", null
                    )
                )
            }
        }

//    override suspend fun fetchMemesContainer(): MemesContainerResult {
//        return try {
//            val response = getDataService.getMemesData()
//            if(response.isSuccessful) {
//                response.body()?.let {
//                    return@let MemesContainerResult.Success(it.data.memes)
//                } ?: MemesContainerResult.Failure(Error("failed to get the data"))
//            } else {
//                MemesContainerResult.Failure(Error("failed to get the data"))
//            }
//
//        } catch (e: Exception) {
//            return MemesContainerResult.Failure(Error("failed to get the data"))
//        }
//    }

}



