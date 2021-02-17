package com.drohealth.pharmacy.dataStore.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.drohealth.pharmacy.dataStore.dao.CartDao
import com.drohealth.pharmacy.model.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)

abstract class PharmacyDatabase : RoomDatabase() {

    abstract fun cartDao() : CartDao

    companion object {
        @Volatile
        private var INSTANCE: PharmacyDatabase? =null

        fun getDatabase(context: Context) : PharmacyDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PharmacyDatabase::class.java,
                    "beneficiary"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}