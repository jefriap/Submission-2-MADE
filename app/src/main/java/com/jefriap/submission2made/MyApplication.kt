package com.jefriap.submission2made

import android.app.Application
import com.jefriap.submission2made.core.di.databaseModule
import com.jefriap.submission2made.core.di.networkModule
import com.jefriap.submission2made.core.di.repositoryModule
import com.jefriap.submission2made.di.useCaseModule
import com.jefriap.submission2made.di.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@FlowPreview
@ExperimentalCoroutinesApi
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}