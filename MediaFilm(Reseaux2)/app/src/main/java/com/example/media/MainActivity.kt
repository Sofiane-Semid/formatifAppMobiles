package com.example.media

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.media.ui.theme.MediaTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MediaTheme {
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
    var films by remember { mutableStateOf<List<FilmClass>>(emptyList()) }
    var quantite by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

    OutlinedTextField(
        value = quantite,
        onValueChange = { quantite = it },
        label = { Text("La quantite de film") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
    )
        Button(
            onClick = {
                val q = quantite.toIntOrNull() ?: 0
                RetroFitMedia.api.listFilm(q).enqueue(object : Callback<List<FilmClass>> {
                    override fun onResponse(
                        call: Call<List<FilmClass>>,
                        response: Response<List<FilmClass>>
                    ) {
                        if (response.isSuccessful) {
                            films = response.body() ?: emptyList()
                        }
                    }

                    override fun onFailure(call: Call<List<FilmClass>>, t: Throwable) {
                        Log.e("EcranReposGitHub", "Erreur lors de la récupération des repos", t)
                    }
                })
            },
    modifier = Modifier.padding(top = 8.dp)
    ) {
        Text("Envoyer")
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)

    ) {
        items(films) { film ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {

                    Text(text = film.titre)

                    Text(text = film.annee)
                }
            }
        }
    }
    }

}












