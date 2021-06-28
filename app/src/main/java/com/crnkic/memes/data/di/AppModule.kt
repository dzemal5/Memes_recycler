package com.crnkic.memes.data.di

import android.content.Context
import androidx.room.Room
import com.crnkic.memes.util.BASE_URL
import com.crnkic.memes.data.localdb.MemesContainerDao
import com.crnkic.memes.data.localdb.MemesRoomDatabase
import com.crnkic.memes.data.network.GetDataService
import com.crnkic.memes.data.repositories.MemesContainerRepository
import com.crnkic.memes.data.repositories.MemesContainerRepositoryImp
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
object AppModule {

    @Singleton
    @Provides
    fun provideMemesRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, MemesRoomDatabase::class.java, "memes_database").build()

    @Singleton
    @Provides
    fun provideMemesDao(database: MemesRoomDatabase) = database.getMemesContainerDao()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
//            .create(Retrofit::class.java)
    }

    @Singleton
    @Provides
    fun provideMemeService(retrofit: Retrofit): GetDataService =
        retrofit.create(GetDataService::class.java)

    @Singleton
    @Provides
    fun provideMemesContainerRepository(memesContainerDao: MemesContainerDao, getDataService: GetDataService) =
        MemesContainerRepository(memesContainerDao, getDataService)


}