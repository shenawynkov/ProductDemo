package com.shenawynkov.productsdemo.viewModel

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shenawynkov.productsdemo.Injection.AppModule
import com.shenawynkov.productsdemo.Injection.NetworkModule
import com.shenawynkov.productsdemo.Injection.component.DaggerViewModelInjector
import com.shenawynkov.productsdemo.Injection.component.ViewModelInjector
import com.shenawynkov.productsdemo.model.Repository
import com.shenawynkov.productsdemo.model.db.AppDb
import com.shenawynkov.productsdemo.model.product.Products
import com.shenawynkov.productsdemo.model.product.Response
import com.shenawynkov.productsdemo.network.ProductApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductsViewModel(var application: Application) : ViewModel() {
    @Inject
    lateinit var repository: Repository
    @Inject
    lateinit var productApi: ProductApi
    @Inject
    lateinit var DB: AppDb
    private lateinit var subscription: Disposable
    public val loading: MutableLiveData<Int> = MutableLiveData()
    lateinit var list: LiveData<List<Products>>

    init {

        val component: ViewModelInjector by lazy {
            DaggerViewModelInjector
                .builder()
                .appModule(AppModule(application))
                .networkModule(NetworkModule)
                .build()

        }
        component.inject(this)

        loadProduts()
    }

    private fun loadProduts() {

        list = repository.list
        if (list.value?.size != null && list.value?.size!! > 0) {
            loading.value = View.INVISIBLE
        }
        subscription = productApi.getProducts().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).doOnSubscribe({ onRetrieveProductListStarted() })
            .doOnTerminate({ onRetrieveProductListEnded() })
            .subscribe({ result -> onRetrieveProductListSuccess(result) },
                { result -> onRetrieveProductListError(result) })


    }

    private fun onRetrieveProductListSuccess(response: Response) {
        repository.populate(response.data)

        Log.v("response_success1 ", "response_success1 ");

    }

    private fun onRetrieveProductListStarted() {
        loading.value = View.VISIBLE

    }

    private fun onRetrieveProductListEnded() {
        loading.value = View.INVISIBLE
    }

    private fun onRetrieveProductListError(error: Throwable) {
        Log.v("response_success1 ", error.localizedMessage);
        Toast.makeText(application, "There is no internet Connection", Toast.LENGTH_LONG).show()

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}