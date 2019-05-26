package com.shenawynkov.productsdemo.model

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import com.shenawynkov.productsdemo.Injection.AppModule
import com.shenawynkov.productsdemo.Injection.NetworkModule
import com.shenawynkov.productsdemo.Injection.component.DaggerViewModelInjector
import com.shenawynkov.productsdemo.Injection.component.ViewModelInjector
import com.shenawynkov.productsdemo.model.db.AppDb
import com.shenawynkov.productsdemo.model.db.ProductDao
import com.shenawynkov.productsdemo.model.product.Products
import javax.inject.Inject

public  class Repository(val app: Application)
{

    @Inject
    lateinit var productDao: ProductDao
    lateinit var list : LiveData<List<Products>>

init {
    val component: ViewModelInjector by lazy {
        DaggerViewModelInjector
            .builder()
            .appModule(AppModule(app))
            .networkModule(NetworkModule)
            .build()

    }
    component.inject(this)
   list=productDao.getAll()
}

        fun populate(list: List<Products>){

            populate(app,productDao).execute(list)
        }

    class populate(app: Application,var productDao: ProductDao):AsyncTask<List<Products>,Void,Void>() {
        init {
            val component: ViewModelInjector by lazy {
                DaggerViewModelInjector
                    .builder()
                    .appModule(AppModule(app))
                    .networkModule(NetworkModule)
                    .build()

            }
            component.inject(this)

        }



        override fun doInBackground(vararg params: List<Products>): Void? {
          productDao.deleteAll()
            var list2=params[0]
            Log.v("listSize0",list2.size.toString())
                for( prod in list2)
            productDao.insertAll(prod)


            return null
        }
    }


}