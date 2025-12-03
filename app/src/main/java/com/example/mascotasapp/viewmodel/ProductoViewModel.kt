package com.example.mascotasapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mascotasapp.data.CarritoDao
import com.example.mascotasapp.data.CarritoEntity
import com.example.mascotasapp.data.ProductoDao
import com.example.mascotasapp.data.ProductoEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductoViewModel(
    private val productoDao: ProductoDao,
    private val carritoDao: CarritoDao
) : ViewModel() {

    val productos: StateFlow<List<ProductoEntity>> =
        productoDao.getAllProductos()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
    val carrito: StateFlow<List<CarritoEntity>> =
        carritoDao.getAllItemsCarrito()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun precargarProductos(lista: List<ProductoEntity>) {
        viewModelScope.launch {
            val existentes = productoDao.obtenerProductosUnaVez()
            val nuevos = lista.filter { nuevo ->
                existentes.none { it.nombre == nuevo.nombre }
            }
            if (nuevos.isNotEmpty()) {
                productoDao.insertarProductos(nuevos)
            }
        }
    }

    fun agregarAlCarrito(producto: ProductoEntity, cantidad: Int) {
        if (cantidad <= 0) return

        viewModelScope.launch {
            val itemExistente = carritoDao.getItemByProductoId(producto.id)

            if (itemExistente != null) {
                val nuevaCantidad = itemExistente.cantidad + cantidad
                val itemActualizado = itemExistente.copy(
                    cantidad = nuevaCantidad
                )
                carritoDao.insert(itemActualizado)
            } else {
                val itemCarrito = CarritoEntity(
                    productoId = producto.id,
                    nombre = producto.nombre,
                    precioUnitario = producto.precio,
                    cantidad = cantidad
                )
                carritoDao.insert(itemCarrito)
            }
        }
    }
    fun eliminarDelCarrito(id: Int) {
        viewModelScope.launch {
            carritoDao.deleteById(id)
        }
    }
    fun pagar() {
        viewModelScope.launch {
            carritoDao.clearAll()
        }
    }
}