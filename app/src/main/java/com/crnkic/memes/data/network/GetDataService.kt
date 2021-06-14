package com.crnkic.memes.data.network

import com.crnkic.memes.data.model.Memes
import retrofit2.Call
import retrofit2.http.GET

interface GetDataService {

    @GET("/get_memes")
    fun getMemesData(): Call<List<Memes>>
}