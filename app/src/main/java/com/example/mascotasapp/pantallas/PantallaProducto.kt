package com.example.mascotasapp.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mascotasapp.R
import com.example.mascotasapp.data.AppDatabase
import com.example.mascotasapp.data.ProductoEntity
import com.example.mascotasapp.viewmodel.ProductoViewModel
import com.example.mascotasapp.viewmodel.ProductoViewModelFactory
import java.text.DecimalFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaProducto() {

    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)

    val factory = ProductoViewModelFactory(db.productoDao(), db.carritoDao())
    val viewModel: ProductoViewModel = viewModel(factory = factory)
    val productos by viewModel.productos.collectAsState()
    val carrito by viewModel.carrito.collectAsState()
    var mostrarCarrito by remember { mutableStateOf(false) }
    val formatoPrecio = remember { DecimalFormat("#,##0.00") }

    LaunchedEffect(carrito.size) {
        if (carrito.isNotEmpty()) mostrarCarrito = true
    }

    LaunchedEffect(Unit) {
        val productosIniciales = listOf(
            ProductoEntity("Correa Larga", 15.50, 10, R.drawable.corre),
            ProductoEntity("Arnés Ajustable", 22.99, 12, R.drawable.cordes),
            ProductoEntity("Collar Antipulgas", 18.75, 20, R.drawable.coo),
            ProductoEntity("Transportadora Mediana", 55.00, 5, R.drawable.traspor),
            ProductoEntity("Juguete Pelota", 5.00, 25, R.drawable.pelota),
            ProductoEntity("Juguete Mordedera", 12.00, 18, R.drawable.jugete),
            ProductoEntity("Ratón de Tela", 4.50, 30, R.drawable.raton),
            ProductoEntity("Croquetas Premium", 32.99, 10, R.drawable.croquetas),
            ProductoEntity("Croquetas Gato", 26.50, 14, R.drawable.wiskas),
            ProductoEntity("Snacks Dentales", 7.99, 20, R.drawable.snacks),
            ProductoEntity("Shampoo Antipulgas", 11.99, 15, R.drawable.shamppo),
            ProductoEntity("Toallitas Húmedas", 6.50, 22, R.drawable.toallitas),
            ProductoEntity("Arena Para Gato", 13.49, 17, R.drawable.arena),
            ProductoEntity("Cama Ortopédica", 45.99, 5, R.drawable.camagrande),
            ProductoEntity("Cama Redonda", 28.90, 9, R.drawable.camasuave),
            ProductoEntity("Rascador Gato", 38.75, 6, R.drawable.rascador),
            ProductoEntity("Vitaminas", 14.99, 12, R.drawable.vitaminas),
            ProductoEntity("Cepillo Dental", 9.25, 19, R.drawable.cepillo),
            ProductoEntity("Cortaúñas", 8.50, 16, R.drawable.corta)
        )
        viewModel.precargarProductos(productosIniciales)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (mostrarCarrito) "Carrito de Compras" else "Productos",
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFB3E5FC),
                    titleContentColor = Color.Black
                )
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFF9C4))
                .padding(padding)
                .padding(12.dp)
        ) {

            // Botón principal de navegación
            if (carrito.isNotEmpty()) {
                Button(
                    onClick = { mostrarCarrito = !mostrarCarrito },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFB3E5FC),
                        contentColor = Color.Black
                    ),
                    elevation = ButtonDefaults.buttonElevation(4.dp)
                ) {
                    Text(
                        if (mostrarCarrito) "← Ver Catálogo de Productos" else "Ver Carrito de Compras (${carrito.size}) →",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }


            // PRODUCTOS
            if (!mostrarCarrito) {
                LazyColumn {
                    items(productos) { producto ->
                        var cantidadSeleccionada by remember { mutableStateOf(1) }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            shape = RoundedCornerShape(15.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            )
                        ) {

                            Row(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Image(
                                    painter = painterResource(id = producto.imagenRes),
                                    contentDescription = producto.nombre,
                                    modifier = Modifier
                                        .size(70.dp)
                                        .clip(CircleShape)
                                        .border(3.dp, Color(0xFFB3E5FC), CircleShape)
                                )

                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(start = 12.dp)
                                ) {
                                    Text(
                                        producto.nombre,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )

                                    Text(
                                        "Precio: $${formatoPrecio.format(producto.precio)}",
                                        color = Color.Black
                                    )

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(top = 4.dp)
                                    ) {
                                        Button(
                                            onClick = { if (cantidadSeleccionada > 1) cantidadSeleccionada-- },
                                            enabled = cantidadSeleccionada > 1,
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = Color(0xFFFF9800),
                                                contentColor = Color.Black
                                            ),
                                            contentPadding = PaddingValues(4.dp),
                                            modifier = Modifier.size(30.dp)
                                        ) {
                                            Text("-", fontSize = 16.sp)
                                        }

                                        Text(
                                            cantidadSeleccionada.toString(),
                                            modifier = Modifier.width(30.dp),
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 16.sp,
                                            color = Color.Black
                                        )

                                        Button(
                                            onClick = { cantidadSeleccionada++ },
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = Color(0xFF4CAF50),
                                                contentColor = Color.Black
                                            ),
                                            contentPadding = PaddingValues(4.dp),
                                            modifier = Modifier.size(30.dp)
                                        ) {
                                            Text("+", fontSize = 16.sp)
                                        }

                                        Spacer(Modifier.width(8.dp))

                                        Button(
                                            onClick = {
                                                viewModel.agregarAlCarrito(producto, cantidadSeleccionada)
                                                cantidadSeleccionada = 1
                                            },
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = Color(0xFFFFF176),
                                                contentColor = Color.Black
                                            )
                                        ) {
                                            Text("Añadir ($cantidadSeleccionada)")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // CARRITO
            if (mostrarCarrito) {

                if (carrito.isEmpty()) {
                    Text(
                        "El carrito está vacío.",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                } else {

                    LazyColumn(modifier = Modifier.weight(1f)) {
                        items(carrito) { item ->

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp),
                                shape = RoundedCornerShape(15.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(12.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Column {
                                        Text(
                                            item.nombre,
                                            fontSize = 18.sp,
                                            color = Color.Black
                                        )

                                        Text(
                                            "Precio: $${formatoPrecio.format(item.precioTotal)} (${item.cantidad} uds.)",
                                            color = Color.Black
                                        )
                                    }

                                    TextButton(onClick = { viewModel.eliminarDelCarrito(item.id) }) {
                                        Text(
                                            "Eliminar",
                                            color = Color(0xFFD32F2F),
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }
                    }

                    val total = carrito.sumOf { it.precioTotal }

                    Spacer(Modifier.height(10.dp))

                    Text(
                        "Total: $${formatoPrecio.format(total)}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Button(
                        onClick = { viewModel.pagar() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFF176),
                            contentColor = Color.Black
                        )
                    ) {
                        Text("Pagar", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}