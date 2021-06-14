package com.crnkic.memes.data.repository

import android.util.Log
import com.crnkic.memes.RESPONSE_PARSING_ERROR_MESSAGE
import com.crnkic.memes.data.model.MemesContainer
import com.crnkic.memes.data.model.MemesContainerResult
import com.crnkic.memes.data.network.GetDataService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import java.lang.Error
import java.lang.Exception
import javax.inject.Inject

class MemesContainerRepository @Inject constructor(private val memeService: GetDataService) : MemesContainerRepositoryImp {
    override suspend fun fetchMemesContainer(): MemesContainerResult =
        withContext(Dispatchers.IO) {
            val memesCall = memeService.getMemesData()
            Log.d("RECYCLER", "Forecast response from backend")

            try {
                Log.d("RECYCLER", "Forecast response from backend in try")
                val response = memesCall.execute()
                Log.d("RECYCLER", "Forecast response from backend in try after execute")

                val memesContainer = response.body()
                Log.d("RECYCLER", "Forecast response from backend" + memesContainer.toString())
                memesContainer?.let {
                    Log.d("RECYCLER", "successInRepository")
                    return@withContext MemesContainerResult.Success(it)
                } ?: run {
                    Log.d("RECYCLER", "failureInRepository")
                    return@withContext MemesContainerResult.Failure(
                        Error(
                            RESPONSE_PARSING_ERROR_MESSAGE

                        )
                    )
                }

            } catch (ex: Exception) {
                Log.d("RECYCLER", "exceptionInRepository")
                return@withContext MemesContainerResult.Failure(Error(ex.message))
            }
        }

//    fun getMemesContainerFromDatabase() : MemesContainerResult{
//
//    }
}