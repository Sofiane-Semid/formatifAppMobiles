package com.example.retrofit

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class GitHubApiInstrumentationTest {
    @Test
    fun testListReposContains5a5() {
        Log.d("TEST", "Entrée dans la fonction testListReposContains5a5")


        // Appel synchrone au service Retrofit existant
        val call = RetroFitInstance.api.listReposInt(" ")
        val response = call.execute()

        // Vérifier que la réponse HTTP est réussie
        assertTrue("Response unsuccessful: ${response.code()}", response.isSuccessful)

        val body = response.body() ?: ""

        // Vérifier que la chaîne contient "5a5"


        Log.d("TEST", "Sortie de la fonction testListReposContains5a5")
    }
}
