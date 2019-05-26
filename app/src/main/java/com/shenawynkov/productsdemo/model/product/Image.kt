package com.shenawynkov.productsdemo.model.product

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    @SerializedName("link") val link: String,
    @SerializedName("height") val height: Int,
    @SerializedName("width") val width: Int
) : Parcelable
