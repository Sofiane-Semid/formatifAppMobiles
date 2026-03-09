package com.example.retrofit


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("exos/long/double/{nombre}")
    fun listReposInt(@Path("nombre") nombre: String): Call<Int>
}