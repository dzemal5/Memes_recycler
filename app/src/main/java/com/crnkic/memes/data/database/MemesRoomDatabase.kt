package com.crnkic.memes.data.database

import android.content.Context
import androidx.room.*
import com.crnkic.memes.data.model.MemesContainer

//@TypeConverters(MemesListConverters::class)

@Database(entities = [MemesContainer::class], version = 1)
@TypeConverters(MemesListConverters::class)
abstract class MemesRoomDatabase : RoomDatabase() {

    abstract fun getMemesContainerDao() : MemesContainerDao

//    companion object {
//        @Volatile
//        private var INSTANCE: MemesRoomDatabase? = null
//        fun getDatabase(context: Context) : MemesRoomDatabase {
//            return INSTANCE?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    MemesRoomDatabase::class.java,
//                    "memes_database"
//                ).fallbackToDestructiveMigration().build()
//                INSTANCE = instance
//                //return instance
//                instance
//            }
//        }
//    }
}