package com.hmelikyan.architecture.view.root.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hmelikyan.architecture.R
import com.hmelikyan.architecture.shared.extensions.isInternetAvailable
import com.hmelikyan.architecture.shared.networking.NetworkError
import com.hmelikyan.architecture.shared.networking.UnauthorizedException
import com.hmelikyan.architecture.view.root.viewCommand.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlin.coroutines.CoroutineContext

open class BaseViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    protected val _viewCommandsLiveData: MutableLiveData<ViewCommand> = MutableLiveData()
     val viewCommands: LiveData<ViewCommand>
        get() = _viewCommandsLiveData

    protected suspend fun <T> handle(
        processingTriple: Flow<Triple<Boolean, Throwable?, T?>?>,
        doOnError: (() -> Any)? = null,
        doOnSuccess: suspend (T?) -> Unit
    ) {
        processingTriple.collect {
            it?.let {
                _viewCommandsLiveData.postValue(ShowLoadingViewCommand(it.first))
                if (!it.first) {
                    if (it.second != null) {
                        exceptionHandler(it.second as Throwable)
                        doOnError?.invoke()
                    } else {
                        doOnSuccess.invoke(it.third)
                    }
                }
            }
        }
    }

    private fun exceptionHandler(exception: Throwable) {
        when (exception) {
            is NetworkError -> {
                if (!isInternetAvailable()) {
                    _viewCommandsLiveData.postValue(NetworkErrorViewCommand())
                } else {
                    _viewCommandsLiveData.postValue(ShowMessageTextViewCommand(exception.getAppErrorMessage()))
                }
            }
            is UnauthorizedException -> {
                _viewCommandsLiveData.postValue(RefreshTokenViewCommand())
            }
            else -> {
                _viewCommandsLiveData.postValue(ShowMessageViewCommand(R.string.default_error_message))
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.IO
}