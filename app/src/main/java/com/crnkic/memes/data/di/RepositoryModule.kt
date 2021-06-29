package com.crnkic.memes.data.di

import com.crnkic.memes.data.localdb.MemesContainerDao
import com.crnkic.memes.data.network.GetDataService
import com.crnkic.memes.data.repositories.MemesContainerRepository
import com.crnkic.memes.data.repositories.MemesContainerRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMemesContainerRepository(memesContainerDao: MemesContainerDao, getDataService: GetDataService) : MemesContainerRepositoryImp {
        return MemesContainerRepository(memesContainerDao, getDataService)
    }
}