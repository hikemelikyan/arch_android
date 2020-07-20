package com.hmelikyan.architecture.shared.networking.root

import com.hmelikyan.architecture.shared.networking.NetworkError
import com.hmelikyan.architecture.shared.extensions.getString
import com.hmelikyan.architecture.R
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import retrofit2.Response

@FlowPreview
@ExperimentalCoroutinesApi
open class BaseService {

    protected fun <T> callAsync(api: suspend () -> Response<T>):Flow<Triple<Boolean,Throwable?,T?>?> {
        val broadcastChannel: ConflatedBroadcastChannel<Triple<Boolean,Throwable?,T?>?> = ConflatedBroadcastChannel()

        CoroutineScope(Job()).launch {
            broadcastChannel.send(Triple(true,null,null))
            proceed(broadcastChannel,api)
        }

        return broadcastChannel.asFlow()
    }

    private suspend fun <T> proceed(broadcastChannel:ConflatedBroadcastChannel<Triple<Boolean,Throwable?,T?>?>,api: suspend () -> Response<T>){
        try {
            api.asFlow()
                .map {
                    /**
                     * response handling depends on Your responseModel
                     * */
                    if (!it.isSuccessful) {
                        throw NetworkError(HttpException(it))
                    }
                    if (it.body() == null) {
                        throw NetworkError(IllegalStateException(getString(R.string.default_error_message)))
                    }
                    it.body()
                }.catch { exception ->
                    broadcastChannel.send(Triple(false,exception,null))
                }
                .collect {
                    broadcastChannel.send(Triple(false,null,it))
                }
        } catch (e: Exception) {
            broadcastChannel.send(Triple(false,e,null))
        }
    }

}