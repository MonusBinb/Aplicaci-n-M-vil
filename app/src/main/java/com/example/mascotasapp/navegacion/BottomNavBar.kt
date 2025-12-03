package com.example.mascotasapp.navegacion

import android.R
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(navController: NavHostController) {

    // Items del menú
    val items = listOf(
        BottomNavItem("Inicio", "inicio", R.drawable.ic_menu_view),
        BottomNavItem("Mascotas", "mascotas", R.drawable.ic_menu_myplaces),
        BottomNavItem("Perfil", "perfil", R.drawable.ic_menu_info_details),
        BottomNavItem("Producto", "producto", android.R.drawable.ic_menu_add) // Icono de compras
    )

    // Barra inferior
    NavigationBar(containerColor = Color(0xFFB3E5FC)) {
        val currentRoute = currentRoute(navController)

        items.forEach { item ->

            // Botón de cada sección
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route) },
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(item.title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF000000),
                    selectedTextColor = Color(0xFF000000),
                    unselectedIconColor = Color.Black,
                    unselectedTextColor = Color.Black
                )
            )
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    // Ruta actual
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}