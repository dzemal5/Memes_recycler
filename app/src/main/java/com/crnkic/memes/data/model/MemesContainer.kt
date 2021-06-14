package com.crnkic.memes.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MemesContainer (
    var success : Boolean,
    var data : Data
) : Parcelable

@Parcelize
data class Memes (
    var id : String,
    var name : String,
    var url : String,
    var width : Int,
    var height : Int,
    @SerializedName("box_count")
    var boxCount : Int
) : Parcelable

@Parcelize
data class Data (
    var memes : List<Memes>
) : Parcelable