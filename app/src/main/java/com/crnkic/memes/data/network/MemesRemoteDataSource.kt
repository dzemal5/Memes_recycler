package com.crnkic.memes.data.network

import android.util.Log
import com.crnkic.memes.RESPONSE_PARSING_ERROR_MESSAGE
import com.crnkic.memes.data.di.NetworkRetrofitModule
import com.crnkic.memes.data.model.MemesContainerResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Error
import java.lang.Exception

object MemesRemoteDataSource {

    suspend fun fetchMemesContainer(): MemesContainerResult =
        withContext(Dispatchers.IO) {
            val memeService = NetworkRetrofitModule.provideRetrofit().create(GetDataService::class.java)
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


}