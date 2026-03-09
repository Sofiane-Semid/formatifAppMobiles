package com.example.myapplication
import org.kickmyb.transfer.RequeteConnexion
import org.kickmyb.transfer.RequeteInscription
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface PostApi {
    /**
     * Inscription d'un nouvel utilisateur
     * POST /id/inscription
     */
    @POST("id/inscription")
    fun inscription(@Body requeteInscription: RequeteInscription): Call<String>

    /**
     * Connexion d'un utilisateur existant
     * POST /id/connexion
     */
    @POST("id/connexion")
    fun connexion(@Body requeteConnexion: RequeteConnexion): Call<String>

    /**
     * Déconnexion de l'utilisateur courant
     * POST /id/deconnexion
     */
    @POST("id/deconnexion")
    fun deconnexion(): Call<String>

    /**
     * Récupération de l'accueil des tâches
     * GET /tache/accueil
     */
    @GET("tache/accueil")
    fun accueilTache(): Call<String>


    @POST("exos/truc/doubler")
    fun doublerTruc(@Body data: PostApi.TrucDoublerData): Call<PostApi.TrucDoublerData>

    data class TrucDoublerData(
        val a: Int,
        val b: String,
        val c: List<Int>
    )
}