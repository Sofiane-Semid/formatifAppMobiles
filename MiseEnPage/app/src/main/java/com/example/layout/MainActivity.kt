package com.example.layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.layout.ui.theme.LayoutTheme
import org.intellij.lang.annotations.JdkConstants

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LayoutTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    navigation()
                }
            }
        }
    }
}
@Composable
fun navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
){
    println("Entrée dans Navigation")
    NavHost(
        navController = navController,
        startDestination = "Layout1",
        //modifier = modifier.padding(padding)
    ) {
        // on passe le navController à l'écran pour qu'il puisse naviguer à son tour
        composable(route = "Layout1") { Layout1(navController, modifier) }
        composable(route = "Layout2") { Layout2(navController, modifier) }

    }
    println("Sortir de Navigation")
}

@Composable
fun Layout1(navController : NavHostController,  modifier: Modifier = Modifier,) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Section 1/2 - Champs USERNAME et PASSWORD
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("USERNAME") },
                    modifier = Modifier
                        .weight(1f)
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("PASSWORD") },
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }

        // Section 1/5 - Vide
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .weight(0.2f)
        )

        // Section 1/5 - Bouton ENVOYER
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    navController.navigate("Layout2")
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
            ) {
                Text("ENVOYER")
            }
        }

        // Section 3/5 - Vide
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .weight(3f)
        )
    }
}

@Composable
fun Layout2(navController : NavHostController,  modifier: Modifier = Modifier){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        // Ligne du haut avec 2 boutons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {}) {
                Text("BOUTON")
            }
            Button(onClick = {}) {
                Text("BOUTON")
            }
        }

        // Contenu central avec carré vert
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Carré vert 50%x50%
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .aspectRatio(1f)
                        .background(
                            color = androidx.compose.ui.graphics.Color(0xFF008080),
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(0.dp)
                        )
                )
            }
        }

        // Ligne du bas avec 2 boutons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {}) {
                Text("BOUTON")
            }
            Button(onClick = {}) {
                Text("BOUTON")
            }
        }
    }
}

