package com.hmelikyan.architecture

import android.app.Application

class App : Application() {

    companion object {
        private lateinit var instance: App

        fun getInstance() =
            if (::instance.isInitialized) instance else throw IllegalStateException("Fail to instantiate application.")
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}