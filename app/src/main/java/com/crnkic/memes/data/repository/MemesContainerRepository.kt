package com.crnkic.memes.data.repository

import com.crnkic.memes.data.database.MemesLocalDataSourceImplementation
import com.crnkic.memes.data.model.MemesContainer
import com.crnkic.memes.data.model.MemesContainerResult
import com.crnkic.memes.data.network.MemesRemoteDataSource
import javax.inject.Inject

class MemesContainerRepository @Inject constructor(
    private val localDataSourceImplementation: MemesLocalDataSourceImplementation
    ) : MemesContainerRepositoryImp {

    override suspend fun fetchMemesContainer(): MemesContainerResult {
        val memesContainerFetchResult = MemesRemoteDataSource.fetchMemesContainer()
        if(memesContainerFetchResult is MemesContainerResult.Success) {
            insertToDatabase(memesContainerFetchResult.memesContainer)
        }
        return memesContainerFetchResult
    }

    override suspend fun getSavedMemesContainer(): MemesContainerResult {
        return localDataSourceImplementation.getSavedMemesContainer()
    }

    override suspend fun insertToDatabase(memesContainer: MemesContainer) {
        localDataSourceImplementation.deleteAll()
        localDataSourceImplementation.insert(memesContainer)
    }

    //    override suspend fun fetchMemesContainer(): MemesContainerResult =
//        withContext(Dispatchers.IO) {
//            val memesCall = memeService.getMemesData()
//
//            try {
//                val response = memesCall.execute()
//
//                val memesContainer = response.body()
//                Log.d("RECYCLER", "Forecast response from backend" + memesContainer.toString())
//                memesContainer?.let {
//                    return@withContext MemesContainerResult.Success(it)
//                } ?: run {
//                    return@withContext MemesContainerResult.Failure(
//                        Error(
//                            RESPONSE_PARSING_ERROR_MESSAGE
//
//                        )
//                    )
//                }
//            } catch (ex: Exception) {
//                Log.e("RECYCLER", "exceptionInRepository")
//                return@withContext MemesContainerResult.Failure(Error(ex.message))
//            }
//        }

//    fun getMemesContainerFromDatabase() : MemesContainerResult{
//
//    }
}