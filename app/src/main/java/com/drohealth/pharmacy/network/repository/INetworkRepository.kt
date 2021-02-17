package com.drohealth.pharmacy.network.repository

import com.drohealth.pharmacy.model.Product
import com.drohealth.pharmacy.utils.ApiResponse

interface INetworkRepository {
    suspend fun getProducts() : ApiResponse<List<Product>>
}