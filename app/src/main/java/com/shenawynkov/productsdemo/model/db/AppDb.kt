package com.shenawynkov.productsdemo.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shenawynkov.productsdemo.model.product.Products
import javax.inject.Singleton

@Singleton
@Database(entities = arrayOf(Products::class), version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun products(): ProductDao
}