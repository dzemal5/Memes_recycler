package com.crnkic.memes.data.di

import com.crnkic.memes.data.database.MemesLocalDataSource
import com.crnkic.memes.data.database.MemesLocalDataSourceImplementation
import com.crnkic.memes.data.model.MemesContainerResult
import com.crnkic.memes.data.network.GetDataService
import com.crnkic.memes.data.repository.MemesContainerRepository
import com.crnkic.memes.data.repository.MemesContainerRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//abstract class RepositoryModule {
//
//    @Singleton
//    @Binds
//    abstract fun bindsMemesContainerRepository(memesLocalDataSourceImp: MemesLocalDataSourceImplementation): MemesContainerRepositoryImp
//
//    @Singleton
//    @Binds
//    abstract fun providesMemesLocalDataSourceImplementation() : MemesContainerResult

//@Module
//@InstallIn(SingletonComponent::class)
//object RepositoryModule {
//
//    @Singleton
//    @Provides
//    fun providesMemesRepository(localDataSource: MemesLocalDataSource): MemesContainerRepository {
//        return MemesContainerRepository(localDataSource)
//    }
//
////        @Singleton
////        @Provides
////        fun providesMemesRepository(localDataSourceImplementation: MemesLocalDataSourceImplementation): MemesContainerRepositoryImp {
////            return MemesContainerRepository(localDataSourceImplementation)
////    }
//}
//}