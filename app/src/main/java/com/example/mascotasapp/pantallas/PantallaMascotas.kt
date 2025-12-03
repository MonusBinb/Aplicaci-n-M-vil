package com.example.mascotasapp.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mascotasapp.R
import com.example.mascotasapp.data.AppDatabase
import com.example.mascotasapp.viewmodel.MascotaViewModel
import com.example.mascotasapp.viewmodel.MascotaViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaMascotas() {

    // Cambiamos a AppDatabase.getDatabase()
    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)

    val factory = MascotaViewModelFactory(db.mascotaDao())
    val viewModel: MascotaViewModel = viewModel(factory = factory)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mascotas en Adopción") },
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
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                //  LISTA DE MASCOTAS
                MascotaCard(
                    id = 1,
                    viewModel = viewModel,
                    imagen = R.drawable.koda,
                    nombre = "Koda",
                    descripcion = "Perro labrador muy juguetón y cariñoso."
                )

                Spacer(modifier = Modifier.height(16.dp))

                MascotaCard(
                    id = 2,
                    viewModel = viewModel,
                    imagen = R.drawable.copito,
                    nombre = "Bolillo",
                    descripcion = "Perro chihuahua viejito, le gusta dormir al sol."
                )

                Spacer(modifier = Modifier.height(16.dp))

                MascotaCard(
                    id = 3,
                    viewModel = viewModel,
                    imagen = R.drawable.bollilo,
                    nombre = "Copito",
                    descripcion = "Gato travieso y curioso, le encanta trepar."
                )

                Spacer(modifier = Modifier.height(16.dp))

                MascotaCard(
                    id = 4,
                    viewModel = viewModel,
                    imagen = R.drawable.max,
                    nombre = "Max",
                    descripcion = "Gato de Jacob."
                )

                Spacer(modifier = Modifier.height(16.dp))

                MascotaCard(
                    id = 5,
                    viewModel = viewModel,
                    imagen = R.drawable.luna,
                    nombre = "Luna",
                    descripcion = "Gatita de Jacob."
                )

                Spacer(modifier = Modifier.height(16.dp))

                MascotaCard(
                    id = 6,
                    viewModel = viewModel,
                    imagen = R.drawable.rocky,
                    nombre = "Rocky",
                    descripcion = "Gatito GOD."
                )

                Spacer(modifier = Modifier.height(16.dp))

                MascotaCard(
                    id = 7,
                    viewModel = viewModel,
                    imagen = R.drawable.michi,
                    nombre = "Michi",
                    descripcion = "Gato muy Michi."
                )

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    )
}

@Composable
fun MascotaCard(
    id: Int,
    viewModel: MascotaViewModel,
    imagen: Int,
    nombre: String,
    descripcion: String
) {

    Card(
        modifier = Modifier
            .widthIn(max = 350.dp)
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {

            Image(
                painter = painterResource(id = imagen),
                contentDescription = nombre,
                modifier = Modifier
                    .size(180.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = nombre,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = descripcion,
                fontSize = 16.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            //   BOTÓN DE ADOPTAR
            Button(
                onClick = { viewModel.adoptarMascota(id) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFF176),
                    contentColor = Color.Black
                )
            ) {
                Text("Adoptar", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(8.dp)) // Espacio entre botones

            //   BOTÓN DE ELIMINAR ADOPCIÓN
            Button(
                onClick = { viewModel.eliminarAdopcion(id) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF5252),
                    contentColor = Color.White
                )
            ) {
                Text("Eliminar Adopción", fontWeight = FontWeight.Bold)
            }
        }
    }
}