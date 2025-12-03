package com.example.mascotasapp.data
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
@Dao
interface CarritoDao {

    // LECTURA REACTIVA
    @Query("SELECT * FROM carrito")
    fun getAllItemsCarrito(): Flow<List<CarritoEntity>>

    @Query("SELECT * FROM carrito WHERE productoId = :idProducto LIMIT 1")
    suspend fun getItemByProductoId(idProducto: Int): CarritoEntity?

    // INSERCIÓN
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CarritoEntity)

    // ACTUALIZACIÓN
    @Update
    suspend fun update(item: CarritoEntity)

    // ELIMINACIÓN POR ID
    @Query("DELETE FROM carrito WHERE id = :id")
    suspend fun deleteById(id: Int)

    // VACIAR CARRITO
    @Query("DELETE FROM carrito")
    suspend fun clearAll()
}