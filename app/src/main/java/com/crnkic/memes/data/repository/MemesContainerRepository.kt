package com.crnkic.memes.data.repository

import android.util.Log
import com.crnkic.memes.RESPONSE_PARSING_ERROR_MESSAGE
import com.crnkic.memes.data.model.MemesContainerResult
import com.crnkic.memes.data.network.GetDataService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Error
import java.lang.Exception
import javax.inject.Inject

class MemesContainerRepository @Inject constructor(private val memeService: GetDataService) : MemesContainerRepositoryImp {
    override suspend fun fetchMemesContainer(): MemesContainerResult =
        withContext(Dispatchers.IO) {
            val memesCall = memeService.getMemesData()

            try {
                val response = memesCall.execute()

                val memesContainer = response.body()
                Log.d("RECYCLER", "Forecast response from backend" + memesContainer.toString())
                memesContainer?.let {
                    return@withContext MemesContainerResult.Success(it)
                } ?: run {
                    return@withContext MemesContainerResult.Failure(
                        Error(
                            RESPONSE_PARSING_ERROR_MESSAGE

                        )
                    )
                }
            } catch (ex: Exception) {
                Log.e("RECYCLER", "exceptionInRepository")
                return@withContext MemesContainerResult.Failure(Error(ex.message))
            }
        }

    override suspend fun getSavedMemesContainer(): MemesContainerResult {
        TODO("Not yet implemented")
    }

//    fun getMemesContainerFromDatabase() : MemesContainerResult{
//
//    }
}