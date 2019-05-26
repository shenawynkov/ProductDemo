package com.shenawynkov.productsdemo.view.productDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.shenawynkov.productsdemo.R
import com.shenawynkov.productsdemo.model.product.Products
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity() {

    lateinit var products: Products

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        products=intent.getParcelableExtra("product")
        init()


    }
    private fun init(){
        tv_title.text=products.name
        tv_description.text=products.productDescription
        tv_price.text=products.price.toString()+" $"
        Glide.with(this).load(products.image.link).circleCrop().into(imageView)


    }
}
