package com.example.media

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetroFitMedia {
    private const val BASE_URL = "https://fourn6-mobile-prof.onrender.com/"

    // Configuration du logging interceptor pour voir les requêtes/réponses HTTP
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Client HTTP avec gestion des cookies de session et logging
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .cookieJar(SessionCookieJar)
        .build()

    // Instance Retrofit avec convertisseurs pour String et JSON
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Interface API prête à l'emploi
    val api: MediaApi = retrofit.create(MediaApi::class.java)




}