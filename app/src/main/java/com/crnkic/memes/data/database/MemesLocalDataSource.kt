package com.crnkic.memes.data.database

import com.crnkic.memes.data.model.MemesContainer
import com.crnkic.memes.data.model.MemesContainerResult
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Error
import javax.inject.Inject


class MemesLocalDataSource @Inject constructor(private val dao: MemesContainerDao) :
    MemesLocalDataSourceImplementation {

    override suspend fun getSavedMemesContainer(): MemesContainerResult =
        withContext(Dispatchers.IO) {
            val forecastContainer = dao.getAll()
            forecastContainer?.let {
                return@withContext MemesContainerResult.Success(it)
            }?: run {
                        return@withContext MemesContainerResult.Failure(Error("Error Message"))
                    }
            }

    override suspend fun insert(memesContainer: MemesContainer) {
        withContext(Dispatchers.IO) {
            dao.insertAll(memesContainer)
        }
    }

    override suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            dao.delete()
        }
    }
}