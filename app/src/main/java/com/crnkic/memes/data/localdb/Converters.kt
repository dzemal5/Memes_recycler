package com.crnkic.memes.data.localdb

import androidx.room.TypeConverter
import com.crnkic.memes.data.model.Data
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MemesListConverters {

    @TypeConverter
    fun memesListToString(memesList: Data) : String {
        val type = object : TypeToken<Data>() {}.type
        return Gson().toJson(memesList, type)
    }

    @TypeConverter
    fun stringToMemesList(memesList: String) : Data {
        val type = object : TypeToken<Data>() {}.type
        return Gson().fromJson(memesList, type)
    }
}