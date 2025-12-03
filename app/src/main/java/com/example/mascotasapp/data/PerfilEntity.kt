package com.example.mascotasapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "perfil_usuario")
data class PerfilEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 1,
    val nombre: String,
    val email: String,
    val descripcion: String
)