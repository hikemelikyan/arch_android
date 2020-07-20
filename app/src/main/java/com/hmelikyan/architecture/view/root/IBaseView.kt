package com.hmelikyan.architecture.view.root

import androidx.annotation.StringRes

interface IBaseView {

    fun defaultError()

    fun onNetworkError()

    fun onError(@StringRes resId: Int)

    fun onError(message: String)

    fun setLightStatusBar()

    fun clearLightStatusBar()

    fun isMainLoading(isLoading: Boolean)

}