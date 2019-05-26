package com.shenawynkov.productsdemo.model

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import com.shenawynkov.productsdemo.Injection.AppModule
import com.shenawynkov.productsdemo.Injection.NetworkModule
import com.shenawynkov.productsdemo.Injection.component.DaggerViewModelInjector
import com.shenawynkov.productsdemo.Injection.component.ViewModelInjector
import com.shenawynkov.productsdemo.model.db.AppDb
import com.shenawynkov.productsdemo.model.product.Products
import javax.inject.Inject

public  class Repository(val app: Application)
{

init {

}

        fun populate(list: List<Products>){

            populate(app).execute(list)
        }

    class populate(app: Application):AsyncTask<List<Products>,Void,Void>() {
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


        @Inject
        lateinit var DB: AppDb
        override fun doInBackground(vararg params: List<Products>): Void? {
          DB.products().deleteAll()
            var list=params[0]
            Log.v("listSize0",list.size.toString())
                for( prod in list)
            DB.products().insertAll(prod)

            return null
        }
    }


}