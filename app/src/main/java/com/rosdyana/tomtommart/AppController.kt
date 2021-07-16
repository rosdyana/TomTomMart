package com.rosdyana.tomtommart

import android.app.Application
import android.content.Context
import com.rosdyana.tomtommart.di.dataModule
import com.rosdyana.tomtommart.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppController: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppController)
            modules(dataModule)
            modules(viewModelModule)
        }
        appInstance = this
    }
    companion object{
        private var appInstance: AppController? = null

        @JvmStatic
        fun getInstance(): Context {
            return appInstance as AppController
        }
    }
}