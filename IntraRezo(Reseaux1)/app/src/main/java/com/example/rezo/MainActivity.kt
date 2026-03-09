package com.example.rezo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rezo.ui.theme.RezoTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RezoTheme {
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
    var representation by remember { mutableStateOf<List<ReprésentationsClass>>(emptyList()) }
    var quantite by remember { mutableStateOf("") }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = {
                val q = quantite.toIntOrNull() ?: 0
                RetroFitInstance.api.listRepresentation(q).enqueue(object :
                    Callback<List<ReprésentationsClass>> {
                    override fun onResponse(
                        call: Call<List<ReprésentationsClass>>,
                        response: Response<List<ReprésentationsClass>>
                    ) {
                        if (response.isSuccessful) {
                            representation = response.body() ?: emptyList()
                        }
                    }

                    override fun onFailure(call: Call<List<ReprésentationsClass>>, t: Throwable) {
                        Log.e("EcranReposGitHub", "Erreur lors de la récupération des repos", t)
                    }
                })
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Envoyer")
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            items(representation) { representations ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = representations.nombre.toString(),

                        )
                        Text(
                            text = representations.description,

                        )
                        Text(
                            text = representations.representation,

                        )
                    }
                }
            }
        }
    }
}
