package com.example.mascotasapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// Tabla producto
@Entity(tableName = "producto")
data class ProductoEntity(
    val nombre: String,
    val precio: Double,
    val stock: Int = 0,
    val imagenRes: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)