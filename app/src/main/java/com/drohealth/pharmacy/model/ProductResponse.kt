package com.drohealth.pharmacy.model

import com.google.gson.annotations.SerializedName

data class ProductResponse(
        @SerializedName("status")
        val status : Int,
        @SerializedName("drugs")
        val drugs : List<Product>
)