package com.drohealth.pharmacy.dataStore.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.drohealth.pharmacy.model.Product

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: Product)

    @Delete
    fun delete(product: Product)

    @Query("SELECT * FROM product")
    fun getCartItems() : LiveData<List<Product>>

    @Query("SELECT Count(*) FROM product ")
    fun getCartCount() : LiveData<Int>
}