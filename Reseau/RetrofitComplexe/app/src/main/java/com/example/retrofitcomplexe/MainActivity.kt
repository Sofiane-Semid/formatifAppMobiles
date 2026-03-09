package com.example.retrofitcomplexe

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.unit.dp

import com.example.retrofitcomplexe.Repo
import com.example.retrofitcomplexe.RetroFitInstance
import com.example.retrofitcomplexe.ui.theme.RetrofitComplexeTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitComplexeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    main(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    @Composable
    fun main(name: String, modifier: Modifier = Modifier) {
        var LeNom by remember { mutableStateOf("") }
        var repos by remember { mutableStateOf<List<Repo>>(emptyList()) }

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
                value = LeNom,
                onValueChange = { LeNom = it },
                label = { Text("Nombree") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {

                        RetroFitInstance.api.listReposInt(LeNom)
                            .enqueue(object : Callback<List<Repo>> {
                                override fun onResponse(
                                    call: Call<List<Repo>>,
                                    response: Response<List<Repo>>
                                ) {
                                    if (response.isSuccessful) {
                                        repos = response.body() ?: emptyList()
                                    }
                                }

                                override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                                    Log.e(
                                        "EcranReposGitHub",
                                        "Erreur lors de la récupération des repos",
                                        t
                                    )
                                }
                            })

                }
            ) {
                Text("Cliquez")
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(repos) { repo ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "ID: ${repo.a}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = repo.b,
                                style = MaterialTheme.typography.titleMedium
                            )

                        }
                    }
                }
            }


        }
    }
}






