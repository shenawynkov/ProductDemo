package com.shenawynkov.productsdemo.view.product

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shenawynkov.productsdemo.R
import com.shenawynkov.productsdemo.model.product.Products
import com.shenawynkov.productsdemo.view.productDetail.ProductDetailActivity
import com.shenawynkov.productsdemo.viewModel.ProductsViewModel
import com.shenawynkov.productsdemo.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class ProductActivity : AppCompatActivity(), ProductListener {

    private lateinit var viewModel: ProductsViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: ProductsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()


    }

    private fun init() {
        //  recycler
        recyclerView = findViewById(R.id.rv_product_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewAdapter = ProductsAdapter(listOf(), this, this)
        recyclerView.adapter = viewAdapter
        //viewmodel
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(this.getApplication())
        ).get(ProductsViewModel::class.java)


        viewModel.list.observe(this, Observer {

            viewAdapter.list = it
            viewAdapter.notifyDataSetChanged()
        })


        viewModel.loading.observe(this, Observer {
            progressBar.visibility = it
        })


    }

    override fun itemSelected(products: Products) {
        var intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra("product", products);
        startActivity(intent)

    }


}
