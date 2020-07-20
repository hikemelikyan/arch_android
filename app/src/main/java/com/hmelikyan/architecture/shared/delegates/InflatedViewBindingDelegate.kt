package com.hmelikyan.architecture.shared.delegates

import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.hmelikyan.architecture.App

class InflatedViewBindingDelegate<T : ViewDataBinding>(
    @LayoutRes private val resId: Int,
    private val context: Context
) : Lazy<T> {

    private var binding: T? = null

    override val value: T
        get() {
            if (binding == null) {
                return DataBindingUtil.inflate<T>(LayoutInflater.from(context), resId, null, false).also { binding = it }
            }
            return binding!!
        }

    override fun isInitialized() = binding != null
}

inline fun <reified I : ViewDataBinding> inflateView(@LayoutRes resId: Int): InflatedViewBindingDelegate<I> =
    InflatedViewBindingDelegate(resId, App.getInstance().applicationContext)