package com.crnkic.memes.data.localdb

import androidx.room.*
import com.crnkic.memes.data.model.Memes
import com.crnkic.memes.data.model.MemesContainer

//@TypeConverters(MemesListConverters::class)

@Database(entities = [Memes::class], version = 1)
@TypeConverters(MemesListConverters::class)
abstract class MemesRoomDatabase : RoomDatabase() {

    abstract fun getMemesContainerDao() : MemesContainerDao
}