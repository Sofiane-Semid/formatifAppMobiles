package com.example.retrofitcomplexe


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ListApi {
    @GET("exos/truc/complexe")
    fun listReposInt(@Query("name") name: String): Call<List<Repo>>
}