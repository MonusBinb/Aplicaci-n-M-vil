package com.example.mascotasapp.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mascotasapp.R
import com.example.mascotasapp.viewmodel.PerfilViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPerfil(viewModel: PerfilViewModel) {

    val perfilActual by viewModel.perfil.collectAsState()

    var nombre by remember { mutableStateOf(perfilActual?.nombre ?: "") }
    var correo by remember { mutableStateOf(perfilActual?.email ?: "") }
    var descripcion by remember { mutableStateOf(perfilActual?.descripcion ?: "") }
    LaunchedEffect(perfilActual) {
        nombre = perfilActual?.nombre ?: ""
        correo = perfilActual?.email ?: ""
        descripcion = perfilActual?.descripcion ?: ""
    }


    val snackbarHostState = remember { SnackbarHostState() } // Snackbar
    val scope = rememberCoroutineScope() // Corrutina

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }, // Notificación
        topBar = {
            TopAppBar(
                title = { Text("Perfil de Usuario") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFB3E5FC),
                    titleContentColor = Color.Black
                )
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFFF9C4))
                    .padding(padding)
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Información de Usuario",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 12.dp),
                    color = Color.DarkGray
                )

                // Foto perfil
                Image(
                    painter = painterResource(id = R.drawable.perfil),
                    contentDescription = "Foto",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color(0xFFB3E5FC), CircleShape)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Nombre
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre de Usuario") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFB3E5FC),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.DarkGray,
                        cursorColor = Color.Black
                    )
                )

                // Correo
                TextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = { Text("Correo electrónico") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFB3E5FC),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.DarkGray,
                        cursorColor = Color.Black
                    )
                )

                // Descripción
                TextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Descripción personal") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFB3E5FC),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.DarkGray,
                        cursorColor = Color.Black
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // BOTÓN GUARDAR
                Button(
                    onClick = {
                        viewModel.guardarPerfil(
                            nombre = nombre,
                            email = correo,
                            descripcion = descripcion
                        )
                        scope.launch {
                            snackbarHostState.showSnackbar("¡Perfil guardado con éxito!")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFB3E5FC),
                        contentColor = Color.Black
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Guardar perfil", fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // BOTÓN ELIMINAR
                if (perfilActual != null) {
                    Button(
                        onClick = {
                            viewModel.eliminarPerfil(perfilActual!!)
                            scope.launch {
                                snackbarHostState.showSnackbar("Perfil eliminado.")
                                nombre = ""
                                correo = ""
                                descripcion = ""
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red.copy(alpha = 0.8f),
                            contentColor = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Eliminar perfil", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    )
}