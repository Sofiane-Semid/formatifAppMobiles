package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.example.myapplication.ui.theme.MyApplicationTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    main(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun main(name: String, modifier: Modifier = Modifier) {
    var reponseDoublerRaw by remember { mutableStateOf("") }
    var requeteEnvoyee by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Text(
            text = "RenvoiPost",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))


        Button(
            onClick = {
                Log.d("EcranKickMyB", "Entrée dans la fonction onClick doublerTruc")
                val data = PostApi.TrucDoublerData(
                    a = 100,
                    b = "gndsadsa",
                    c = listOf(8, 12, 14, 18, 1998)
                )
                requeteEnvoyee = com.google.gson.Gson().toJson(data)
                RetrofitInstance.api.doublerTruc(data).enqueue(object : Callback<PostApi.TrucDoublerData> {
                    override fun onResponse(
                        call: Call<PostApi.TrucDoublerData>,
                        response: Response<PostApi.TrucDoublerData>
                    ) {
                        Log.d("EcranKickMyB", "Entrée dans la fonction onResponse doublerTruc : code=${response.code()}")
                        if (response.isSuccessful) {

                            // Affichage brut JSON
                            reponseDoublerRaw = response.body()?.let { com.google.gson.Gson().toJson(it) } ?: "Pas de données"
                        } else {

                            reponseDoublerRaw = response.errorBody()?.string() ?: "Erreur HTTP: ${'$'}{response.code()}"
                        }
                        Log.d("EcranKickMyB", "Sortie de la fonction onResponse doublerTruc")
                    }

                    override fun onFailure(call: Call<PostApi.TrucDoublerData>, t: Throwable) {
                        Log.d("EcranKickMyB", "Entrée dans la fonction onFailure doublerTruc : ${'$'}{t.message}")
                        Log.e("EcranKickMyB", "Erreur doublerTruc", t)

                        reponseDoublerRaw = "Erreur: ${'$'}{t.message}"
                        Log.d("EcranKickMyB", "Sortie de la fonction onFailure doublerTruc")
                    }
                })
                Log.d("EcranKickMyB", "Sortie de la fonction onClick doublerTruc")
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Doubler Truc (POST)")
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (requeteEnvoyee.isNotEmpty()) {
            Text(
                text = "Requête envoyée :\n$requeteEnvoyee",
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (reponseDoublerRaw.isNotEmpty()) {
            Text(
                text = "Réponse reçue :\n$reponseDoublerRaw",
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )
        }
    }
}

