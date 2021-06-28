package com.crnkic.memes.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.crnkic.memes.data.localdb.MemesContainerDao
import com.crnkic.memes.data.model.Memes
import com.crnkic.memes.data.model.MemesContainerResult
import com.crnkic.memes.data.network.GetDataService
import java.lang.Error
import javax.inject.Inject

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

    override suspend fun fetchMemesContainer(): MemesContainerResult {
        return try {
            Log.d("Fetch_Data", "fetching data")

            val response = getDataService.getMemesData()
            if (response.isSuccessful) {
                Log.d("Fetch_Data", "fetching data")
                response.body()?.let {
                    return@let MemesContainerResult.Success(it)
                } ?: MemesContainerResult.Failure(Error("An unknown error occured"))
            } else {
                MemesContainerResult.Failure(Error("An unknown error occured"))
            }
        } catch (e: Exception) {
            MemesContainerResult.Failure(Error("Coundnt reach the server"))
        }
    }
}




//    override suspend fun fetchMemesContainer(): MemesContainerResult =
//        withContext(Dispatchers.IO) {
//            val memesCall : Call<List<Memes>> = getDataService.getMemesData()
//            try {
//                val response = memesCall.execute()
//                val memes = response.body()
//
//                memes?.let {
//                    return@withContext MemesContainerResult.Success(it)
//                } ?: run {
//                    return@withContext MemesContainerResult.Failure(
//                        Error("An unknown error occured")
//                    )
//                }
//            } catch (e: Exception) {
//                return@withContext MemesContainerResult.Failure(
//                    Error("Couldn't reach the server. Check your internet connection", null
//                    )
//                )
//            }
//        }



