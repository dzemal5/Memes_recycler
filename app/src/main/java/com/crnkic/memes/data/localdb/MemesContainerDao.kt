package com.crnkic.memes.data.localdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.crnkic.memes.data.model.Memes

@Dao
interface MemesContainerDao {
    @Query("SELECT * FROM memesContainerTable LIMIT 1")
    fun observeAllMemesItems(): LiveData<List<Memes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMemeItem(memes: List<Memes>)

    @Delete
    suspend fun deleteMemeItem(memes: List<Memes>)
}