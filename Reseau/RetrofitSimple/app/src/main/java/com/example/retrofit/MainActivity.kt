package com.example.retrofit

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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.unit.dp
import com.example.retrofit.ui.theme.RetrofitTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitTheme {
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
    var LeNombre by remember { mutableStateOf("") }
    var donnees:Int? by remember { mutableStateOf(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "Nombre",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = LeNombre,
            onValueChange = { LeNombre = it },
            label = { Text("Nombree") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier.height(15.dp))

        Button(
            onClick = {
                Log.d("EcranReposGitHub", "Entrée dans la fonction onClick : Nombre=$LeNombre")
                RetroFitInstance.api.listReposInt(LeNombre).enqueue(object : Callback<Int> {
                    override fun onResponse(call: Call<Int>, response: Response<Int>) {
                        Log.d("EcranReposGitHub", "Entrée dans la fonction onResponse : code=${response.code()}")
                        if (response.isSuccessful) {
                            donnees = response.body()
                        }
                        Log.d("EcranReposGitHub", "Sortie de la fonction onResponse")
                    }

                    override fun onFailure(call: Call<Int>, t: Throwable) {
                        Log.d("EcranReposGitHub", "Entrée dans la fonction onFailure : ${t.message}")
                        Log.e("EcranReposGitHub", "Erreur lors de la récupération des repos", t)
                        Log.d("EcranReposGitHub", "Sortie de la fonction onFailure")
                    }
                })
                Log.d("EcranReposGitHub", "Sortie de la fonction onClick")
            },

        ) {
            Text("Cliquez")

        }

        Spacer(modifier.height(16.dp))

        Text(
            text = "$donnees",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

    }
}



