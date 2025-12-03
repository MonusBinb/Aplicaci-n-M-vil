package com.example.mascotasapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductoDao {

    //  LECTURA REACTIVA
    @Query("SELECT * FROM producto ORDER BY nombre ASC")
    fun getAllProductos(): Flow<List<ProductoEntity>>

    //  INSERCIÓN
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(producto: ProductoEntity)

    //  ACTUALIZACIÓN
    @Update
    suspend fun update(producto: ProductoEntity)

    //  LECTURA POR ID
    @Query("SELECT * FROM producto WHERE id = :id")
    fun getProducto(id: Int): Flow<ProductoEntity>

    // Obtener lista solo una vez
    @Query("SELECT * FROM producto")
    suspend fun obtenerProductosUnaVez(): List<ProductoEntity>

    // Insertar lista completa
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarProductos(productos: List<ProductoEntity>)
}