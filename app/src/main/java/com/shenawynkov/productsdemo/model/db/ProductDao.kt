package com.shenawynkov.productsdemo.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.shenawynkov.productsdemo.model.product.Products

@Dao
interface ProductDao {


    @Query("SELECT * FROM products")
    fun getAll(): LiveData<List<Products>>

    @Insert
    fun insertAll(vararg products: Products)

    @Query("DELETE FROM products")
    fun deleteAll()
}