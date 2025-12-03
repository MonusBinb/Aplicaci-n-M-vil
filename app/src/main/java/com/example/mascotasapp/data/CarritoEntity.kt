package com.example.mascotasapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carrito")
data class CarritoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val productoId: Int,         // ID del producto original
    val nombre: String,          // Nombre del producto
    val precioUnitario: Double,  // Precio por unidad del producto
    val cantidad: Int            // Cantidad de este producto en el carrito
) {
    val precioTotal: Double
        get() = precioUnitario * cantidad
}