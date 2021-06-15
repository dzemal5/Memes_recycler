package com.crnkic.memes.data.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "memesContainerTable")
data class MemesContainer(
        @Expose(deserialize = false, serialize = false)
        @PrimaryKey(autoGenerate = false)
        var id: Int = 0,
        var success: Boolean,
        var data: Data
) : Parcelable

@Parcelize
data class Memes(
        var id: String,
        var name: String,
        var url: String,
        var width: Int,
        var height: Int,
        @SerializedName("box_count")
        var boxCount: Int
) : Parcelable

@Parcelize
data class Data(
        @Embedded
        var memes: List<Memes>
) : Parcelable