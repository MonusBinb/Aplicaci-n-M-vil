package com.example.mascotasapp.navegacion

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mascotasapp.data.AppDatabase
import com.example.mascotasapp.pantallas.PantallaInicio
import com.example.mascotasapp.pantallas.PantallaMascotas
import com.example.mascotasapp.pantallas.PantallaPerfil
import com.example.mascotasapp.pantallas.PantallaProducto
import com.example.mascotasapp.viewmodel.PerfilViewModel
import com.example.mascotasapp.viewmodel.PerfilViewModelFactory

@Composable
fun MyApp() {
    val navController = rememberNavController()

    val context = LocalContext.current
    val perfilDao = AppDatabase.getDatabase(context).perfilDao()
    val perfilViewModelFactory = PerfilViewModelFactory(perfilDao)

    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "inicio",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("inicio") { PantallaInicio() }
            composable("mascotas") { PantallaMascotas() }

            composable("perfil") {
                val perfilViewModel: PerfilViewModel = viewModel(factory = perfilViewModelFactory)

                PantallaPerfil(viewModel = perfilViewModel)
            }

            composable("producto") { PantallaProducto() }
        }
    }
}