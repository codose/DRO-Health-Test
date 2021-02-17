package com.drohealth.pharmacy.network.api

import com.drohealth.pharmacy.model.Product
import com.drohealth.pharmacy.model.ProductResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*

/*
    Created By : Osemwingie Oshodin
    Github : https://github.com/codose
 */

interface ApiService {

    @GET("api/drugs/get")
    fun getProducts() : Deferred<ProductResponse>

}