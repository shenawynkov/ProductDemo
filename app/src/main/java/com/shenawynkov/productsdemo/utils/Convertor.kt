package com.shenawynkov.productsdemo.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shenawynkov.productsdemo.model.product.Products
import java.util.*


class Convertor {


    companion object {
        var gson = Gson()
        @TypeConverter
        fun stringToSomeObjectList(data: String?): List<Products> {
            if (data == null) {
                return Collections.emptyList()
            }

            val listType = object : TypeToken<List<Products>>() {

            }.type

            return gson.fromJson(data, listType)
        }

        @TypeConverter
        fun someObjectListToString(someObjects: List<Products>): String {
            return gson.toJson(someObjects)
        }
    }
}