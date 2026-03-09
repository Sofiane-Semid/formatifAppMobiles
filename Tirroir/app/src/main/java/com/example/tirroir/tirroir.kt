package com.example.tirroir



import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.navigation.NavHostController

@Composable
fun MonSuperTiroir(
    controleurNavigation: NavHostController,
    routeActuelle: String,
    fermerTiroir: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalDrawerSheet(modifier) {
        JetNewsLogo(
            modifier = Modifier.padding(horizontal = 28.dp, vertical = 24.dp)
        )
        NavigationDrawerItem(
            label = { Text("Sofiane") },
            icon = { Icon(Icons.Filled.Home, null) },
            selected = routeActuelle == "EcranYo",
            onClick = {
                controleurNavigation.navigate("EcranYo")
                fermerTiroir()
            },

            )
        NavigationDrawerItem(
            label = { Text("Quitter") },
            icon = { Icon(Icons.Filled.Add, null) },
            selected = routeActuelle == "connexion",
            onClick = {
                controleurNavigation.navigate("connexion")
                fermerTiroir()
            },

            )

    }
}

@Composable
fun JetNewsLogo(modifier: Modifier) {
    Text(
        text = "Yo nom",
        modifier = modifier
    )
}