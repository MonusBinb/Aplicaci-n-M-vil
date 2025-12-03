package com.example.mascotasapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mascotasapp.data.PerfilDao
import com.example.mascotasapp.data.PerfilEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PerfilViewModel(private val dao: PerfilDao) : ViewModel() {


    val perfil = dao.obtenerPerfil().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    fun guardarPerfil(nombre: String, email: String, descripcion: String) {
        viewModelScope.launch {
            val nuevoPerfil = PerfilEntity(
                nombre = nombre,
                email = email,
                descripcion = descripcion
            )
            dao.guardarPerfil(nuevoPerfil)
        }
    }

    fun eliminarPerfil(perfil: PerfilEntity) {
        viewModelScope.launch {
            // Llama a la función del DAO
            dao.eliminarPerfil(perfil)
        }
    }
}