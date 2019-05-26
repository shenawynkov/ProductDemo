package com.shenawynkov.productsdemo.model.product

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "products")
@Parcelize
data class Products(
    @SerializedName("id")
    @PrimaryKey
    val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("productDescription") val productDescription: String,
    @Embedded
    @SerializedName("image") val image: Image,
    @SerializedName("price") val price: Int
) : Parcelable