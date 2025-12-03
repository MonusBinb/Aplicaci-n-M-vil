package com.example.mascotasapp.data

import androidx.room.*
@Dao
interface MascotaDao {

    @Query("SELECT * FROM mascotas")
    suspend fun obtenerMascotas(): List<MascotaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarMascota(mascota: MascotaEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarMascotas(mascotas: List<MascotaEntity>)

    @Update
    suspend fun actualizarMascota(mascota: MascotaEntity)

    // Función que marca la mascota como adoptada
    @Query("UPDATE mascotas SET adoptada = :estado WHERE id = :mascotaId")
    suspend fun marcarAdoptada(mascotaId: Int, estado: Boolean = true)

    // Deshacer la adopción
    @Query("UPDATE mascotas SET adoptada = 0 WHERE id = :mascotaId")
    suspend fun deshacerAdopcion(mascotaId: Int)
}