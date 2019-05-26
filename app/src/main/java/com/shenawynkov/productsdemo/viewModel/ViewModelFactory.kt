package com.shenawynkov.productsdemo.viewModel

import androidx.lifecycle.ViewModel
import android.app.Application
import androidx.lifecycle.ViewModelProvider


class ViewModelFactory(private val mApplication: Application) :
    ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductsViewModel(mApplication) as T
    }
}