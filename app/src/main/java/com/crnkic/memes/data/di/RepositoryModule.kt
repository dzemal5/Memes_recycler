package com.vladkryvovyaz.mvvm_hilt_test.di

import com.crnkic.memes.data.network.GetDataService
import com.crnkic.memes.data.repository.MemesContainerRepository
import com.crnkic.memes.data.repository.MemesContainerRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMemesRepository(memeService: GetDataService): MemesContainerRepositoryImp {
        return MemesContainerRepository(memeService)
    }
}