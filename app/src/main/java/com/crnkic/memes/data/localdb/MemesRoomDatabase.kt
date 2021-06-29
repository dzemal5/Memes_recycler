package com.crnkic.memes.data.localdb

import androidx.room.*
import com.crnkic.memes.data.model.Memes

@TypeConverters(MemesListConverters::class)
@Database(entities = [Memes::class], version = 1)
abstract class MemesRoomDatabase : RoomDatabase() {

    abstract fun getMemesContainerDao() : MemesContainerDao
}