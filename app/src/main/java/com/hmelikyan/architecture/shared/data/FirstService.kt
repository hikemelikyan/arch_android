package com.hmelikyan.architecture.shared.data

import com.hmelikyan.architecture.shared.delegates.networkProvider
import com.hmelikyan.architecture.shared.networking.root.BaseService

class FirstService private constructor(): BaseService(){

    companion object {
        private lateinit var INSTANCE: FirstService

        fun getInstance(): FirstService {
            if (!::INSTANCE.isInitialized) INSTANCE = FirstService()
            return INSTANCE
        }
    }

    private val mService:IFirstService by networkProvider()

    fun getPosts(id:Int) = callAsync { mService.getTodosForUser(id) }

}