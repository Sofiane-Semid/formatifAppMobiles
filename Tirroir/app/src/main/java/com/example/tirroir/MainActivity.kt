package com.example.tirroir

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tirroir.MonSuperTiroir
import com.example.tirroir.ui.theme.TirroirTheme
import kotlinx.coroutines.launch
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TirroirTheme {
                Navigate()
            }
        }
    }
}

@Composable
fun Navigate(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    println("Entrée dans Navigation")
    NavHost(
        navController = navController,
        startDestination = "EcranYo",
    ) {
        composable(route = "EcranYo") { EcranYo(modifier, navController) }
        composable(route = "connexion") { Connexion(modifier, navController) }
    }
    println("Sortir de Navigation")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EcranYo(modifier: Modifier = Modifier, navController: NavHostController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MonSuperTiroir(
                controleurNavigation = navController,
                routeActuelle = "EcranYo",
                fermerTiroir = { coroutineScope.launch { drawerState.close() } }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Yo Semid") },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                coroutineScope.launch { drawerState.open() }
                            }
                        ) {
                            Icon(Icons.Filled.Menu, contentDescription = null)
                        }
                    }
                )
            },
            content = { paddingValues ->
                // Vide, car l'écran principal n'a pas de contenu spécifique selon le schéma
                Box(modifier = Modifier.padding(paddingValues))
            }
        )
    }
}

@Composable
fun Connexion(modifier: Modifier = Modifier, navHostController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .padding(20.dp)
                        .size(width = 20.dp, height = 150.dp)
                        .background(Color.Blue)

                ) {
                    Text(
                        text = "Logo",
                        color = Color.White,
                        modifier = Modifier.padding()
                    )
                }
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("COURRIEL") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("MOT DE PASSE") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
            }
            Button(
                onClick = {navHostController.navigate("EcranYo")},
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 90.dp, end = 16.dp)
            ) { Text("Go") }


            FloatingActionButton(
                onClick = { navHostController.navigate("EcranYo") },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .size(60.dp),
                shape = CircleShape
            ) {
                Text("GO\n1/2")
            }
        }
    }
}