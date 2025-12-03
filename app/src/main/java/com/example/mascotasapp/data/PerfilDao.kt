package com.example.mascotasapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PerfilDao {

    // FUNCIÓN GUARDAR / ACTUALIZAR
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardarPerfil(perfil: PerfilEntity)

    // FUNCIÓN OBTENER
    @Query("SELECT * FROM perfil_usuario WHERE id = 1")
    fun obtenerPerfil(): Flow<PerfilEntity?>

    // FUNCIÓN ELIMINAR
    @Delete
    suspend fun eliminarPerfil(perfil: PerfilEntity)
}