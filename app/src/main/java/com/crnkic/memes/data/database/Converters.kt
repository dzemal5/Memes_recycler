package com.crnkic.memes.data.database

import androidx.room.TypeConverter
import com.crnkic.memes.data.model.Data
import com.crnkic.memes.data.model.Memes
import com.crnkic.memes.data.model.MemesContainer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MemesListConverters {

    @TypeConverter
    fun MemesListToString(memesList: Data) : String {
        val type = object : TypeToken<Data>() {}.type
        return Gson().toJson(memesList, type)
    }

    @TypeConverter
    fun StringToMemesList(memesList: String) : Data {
        val type = object : TypeToken<Data>() {}.type
        return Gson().fromJson(memesList, type)
    }
}