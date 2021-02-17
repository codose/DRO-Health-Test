package com.drohealth.pharmacy.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Product")
@Parcelize
data class Product(
    @PrimaryKey
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("type")
    val type : String,
    @SerializedName("vendor")
    val vendor : String,
    @SerializedName("price")
    val price : Int,
    @SerializedName("size")
    val size : String,
    @SerializedName("product_id")
    val productId : String,
    @SerializedName("constituents")
    val constituents : String,
    @SerializedName("dispensed_in")
    val dispensedIn : String,
    @SerializedName("description")
    val description : String,
    @SerializedName("image_url")
    val imageUrl : String,
    @SerializedName("created_at")
    val createdAt : String,
    @SerializedName("updated_at")
    val updatedAt : String,
    var count : Int = 1
) : Parcelable
