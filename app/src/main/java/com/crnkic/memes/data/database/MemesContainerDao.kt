package com.crnkic.memes.data.database

import androidx.room.*
import com.crnkic.memes.data.model.MemesContainer

@Dao
interface MemesContainerDao {
    @Query("SELECT * FROM memesContainerTable LIMIT 1")
    fun getAll(): MemesContainer?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(memesContainer: MemesContainer)

    @Query("DELETE FROM memesContainerTable")
    suspend fun delete()

}