package com.example.mascotasapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mascotasapp.data.MascotaDao
import com.example.mascotasapp.data.MascotaEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MascotaViewModel(private val mascotaDao: MascotaDao) : ViewModel() {

    private val _mascotas = MutableStateFlow<List<MascotaEntity>>(emptyList())
    val mascotas: StateFlow<List<MascotaEntity>> get() = _mascotas

    init {
        viewModelScope.launch {
            val existentes = mascotaDao.obtenerMascotas()

            if (existentes.isEmpty()) {
                mascotaDao.insertarMascotas(
                    listOf(
                        MascotaEntity(id=0, "Koda", "Perro labrador muy juguetón y cariñoso."),
                        MascotaEntity(id=0, "Bolillo", "Perro chihuahua viejito, le gusta dormir al sol."),
                        MascotaEntity(id=0, "Copito", "Gato travieso y curioso."),
                        MascotaEntity(id=0, "Max", "Gato de Jacob."),
                        MascotaEntity(id=0, "Luna", "Gatita de Jacob."),
                        MascotaEntity(id=0, "Rocky", "Gatito GOD."),
                        MascotaEntity(id=0, "Michi", "Gato muy Michi.")
                    )
                )
            }

            cargarMascotas()
        }
    }

    private fun cargarMascotas() {
        viewModelScope.launch {
            _mascotas.value = mascotaDao.obtenerMascotas()
        }
    }

    fun adoptarMascota(id: Int) {
        viewModelScope.launch {
            // Llama a la función del DAO para marcar como adoptada
            mascotaDao.marcarAdoptada(id)
            cargarMascotas()
        }
    }

    // Para eliminar o deshacer la adopción
    fun eliminarAdopcion(id: Int) {
        viewModelScope.launch {
            mascotaDao.deshacerAdopcion(id)
            cargarMascotas()
        }
    }
}