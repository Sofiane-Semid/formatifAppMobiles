package com.example.media

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface MediaApi {

    @GET("films/{quantite}")
    fun listFilm(@Path("quantite") quantite: Int): Call<List<FilmClass>>

}