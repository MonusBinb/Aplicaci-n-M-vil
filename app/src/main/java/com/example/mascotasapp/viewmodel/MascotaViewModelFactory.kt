package com.example.mascotasapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mascotasapp.data.MascotaDao

class MascotaViewModelFactory(
    private val mascotaDao: MascotaDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MascotaViewModel::class.java)) {
            return MascotaViewModel(mascotaDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
