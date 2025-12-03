package com.example.mascotasapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "mascotas")
data class MascotaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val descripcion: String,
    val adoptada: Boolean = false
)