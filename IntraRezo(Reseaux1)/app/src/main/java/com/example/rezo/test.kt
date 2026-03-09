package com.example.rezo

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class test {
    @Test
    fun testListReposContains5a5() {
        Log.d("TEST", "Entrée dans la fonction testListReposContains5a5")

        // Appel synchrone au service Retrofit existant
        val call = RetroFitInstance.api.listRepresentation(12)
        val response = call.execute()

        // Vérifier que la réponse HTTP est réussie
        assertTrue("Response unsuccessful: ${response.code()}", response.isSuccessful)

        val body = response.body() ?: ""



        Log.d("TEST", "Sortie de la fonction testListReposContains5a5")
    }
}