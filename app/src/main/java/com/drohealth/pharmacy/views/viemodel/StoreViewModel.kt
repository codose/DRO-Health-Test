package com.drohealth.pharmacy.views.viemodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drohealth.pharmacy.dataStore.database.PharmacyDatabase
import com.drohealth.pharmacy.model.Product
import com.drohealth.pharmacy.network.repository.NetworkRepositoryImpl
import com.drohealth.pharmacy.utils.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StoreViewModel(application: Application) : AndroidViewModel(application) {
    private val networkRepository = NetworkRepositoryImpl()

    private val database = PharmacyDatabase.getDatabase(application.applicationContext)

    val cartCount = database.cartDao().getCartCount()

    val cartItems = database.cartDao().getCartItems()

    val products = MutableLiveData<ApiResponse<List<Product>>>()

    init {
        getProducts()
    }
    fun getProducts() {
        products.value = ApiResponse.Loading()
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                val data = networkRepository.getProducts()
                products.postValue(data)
            }
        }
    }

    fun addToCart(product: Product) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                database.cartDao().insert(product)
            }
        }
    }

    fun removeFromCart(product: Product) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                database.cartDao().delete(product)
            }
        }
    }
}