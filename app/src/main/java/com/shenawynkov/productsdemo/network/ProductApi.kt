package com.shenawynkov.productsdemo.network

import com.shenawynkov.productsdemo.model.product.Products
import com.shenawynkov.productsdemo.model.product.Response
import io.reactivex.Observable
import retrofit2.http.GET

interface ProductApi {


    @GET(".")
    fun getProducts():Observable<Response>
}
