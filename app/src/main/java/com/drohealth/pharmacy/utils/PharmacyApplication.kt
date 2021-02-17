package com.drohealth.pharmacy.utils

import android.app.Application
import com.drohealth.pharmacy.BuildConfig
import timber.log.Timber

class PharmacyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}