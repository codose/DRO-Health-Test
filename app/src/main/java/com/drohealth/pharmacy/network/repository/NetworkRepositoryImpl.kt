package com.drohealth.pharmacy.network.repository

import com.drohealth.pharmacy.model.Product
import com.drohealth.pharmacy.network.api.RetrofitClient
import com.drohealth.pharmacy.utils.ApiResponse
import retrofit2.HttpException

class NetworkRepositoryImpl : INetworkRepository {
    private val api = RetrofitClient.apiService()
    override suspend fun getProducts(): ApiResponse<List<Product>> {
        return try {
            val products = api.getProducts().await()
            ApiResponse.Success(products.drugs)
        }catch (e : HttpException){
            ApiResponse.Failure(e.message(), e.code())
        }catch (t : Throwable){
            ApiResponse.Failure(t.message!!)
        }
    }

}