package com.oldgoat5.udemo

import android.app.Application
import com.oldgoat5.udemo.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class UDemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@UDemoApplication)
            androidLogger()
            modules(appModule)
        }
    }
}
