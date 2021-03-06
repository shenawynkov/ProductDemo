package com.shenawynkov.productsdemo.Injection

import android.app.Application
import androidx.room.Room
import com.shenawynkov.productsdemo.model.Repository
import com.shenawynkov.productsdemo.model.db.AppDb
import com.shenawynkov.productsdemo.model.db.ProductDao
import com.shenawynkov.productsdemo.utils.db
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {


    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDb = Room.databaseBuilder(
        app,
        AppDb::class.java, db
    ).build()


    @Provides
    @Reusable

    fun provideProductDao(
        database: AppDb
    ): ProductDao = database.products()


    @Provides
    @Singleton
    fun provideRepostory(
    ): Repository {

        return Repository(application)
    }
}