package com.example.mascotasapp.navegacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mascotasapp.ui.theme.MascotasAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Carga UI
        setContent {
            MascotasAppTheme { // Tema app
                MyApp()        // Pantallas
            }
        }
    }
}
