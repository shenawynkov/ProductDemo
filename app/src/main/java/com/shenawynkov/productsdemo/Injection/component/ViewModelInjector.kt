package com.shenawynkov.productsdemo.Injection.component

import com.shenawynkov.productsdemo.Injection.AppModule
import com.shenawynkov.productsdemo.Injection.NetworkModule
import com.shenawynkov.productsdemo.model.Repository
import com.shenawynkov.productsdemo.viewModel.ProductsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetworkModule::class, AppModule::class))
public interface ViewModelInjector {

    fun inject(productsViewModel: ProductsViewModel)
    fun inject(repository: Repository)
    fun inject(populate: Repository.populate)


}