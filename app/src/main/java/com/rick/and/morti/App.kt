package com.rick.and.morti

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import com.rick.and.morti.di.dataBaseModule
import com.rick.and.morti.di.networkModule
import com.rick.and.morti.di.repositoryModule
import com.rick.and.morti.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    viewModelModule,
                    repositoryModule,
                    dataBaseModule
                )
            )
        }
    }

}