package com.example.mascotasapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mascotasapp.data.CarritoDao
import com.example.mascotasapp.data.ProductoDao

class ProductoViewModelFactory(
    private val productoDao: ProductoDao,
    private val carritoDao: CarritoDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductoViewModel(productoDao, carritoDao) as T
        }
        throw IllegalArgumentException("ViewModel desconocido")
    }
}