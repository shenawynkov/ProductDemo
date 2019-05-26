package com.shenawynkov.productsdemo.model.product

import com.google.gson.annotations.SerializedName


data class Response(	@SerializedName("data") val data : List<Products>,
                           @SerializedName("count") val count : Int)
