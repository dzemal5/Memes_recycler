package com.crnkic.memes.data.di

import android.content.Context
import androidx.room.Room
import com.crnkic.memes.BASE_URL
import com.crnkic.memes.data.database.MemesContainerDao
import com.crnkic.memes.data.database.MemesLocalDataSourceImplementation
import com.crnkic.memes.data.database.MemesRoomDatabase
import com.crnkic.memes.data.network.GetDataService
import com.crnkic.memes.data.repository.MemesContainerRepository
import com.crnkic.memes.data.repository.MemesContainerRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkRetrofitModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideMemeService(retrofit: Retrofit): GetDataService =
        retrofit.create(GetDataService::class.java)

    @Provides
    @Singleton
    fun memesRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, MemesRoomDatabase::class.java, "memes_database").build()

    @Provides
    @Singleton
    fun provideMemesContainerDao(database: MemesRoomDatabase) = database.getMemesContainerDao()

    @Singleton
    @Binds
    fun bindsMemesContainerRepository(memesLocalDataSourceImp: MemesLocalDataSourceImplementation) = MemesContainerRepository(memesLocalDataSourceImp)
}