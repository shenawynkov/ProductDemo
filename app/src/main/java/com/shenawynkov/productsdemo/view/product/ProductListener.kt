package com.shenawynkov.productsdemo.view.product

import com.shenawynkov.productsdemo.model.product.Products

public interface ProductListener{

    fun itemSelected(products: Products)
}