package com.hmelikyan.architecture.shared.delegates

import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.hmelikyan.architecture.view.root.BaseActivity
import kotlin.reflect.KProperty

class ContentViewBindingDelegate<in R : BaseActivity, out T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) {

    private var binding: T? = null

    operator fun getValue(activity: R, property: KProperty<*>): T {
        if (binding == null) {
            binding = DataBindingUtil.setContentView<T>(activity, layoutRes).apply {
                lifecycleOwner = activity
            }
        }
        return binding!!
    }
}

fun <R : BaseActivity, T : ViewDataBinding> contentView(
    @LayoutRes layoutRes: Int
): ContentViewBindingDelegate<R, T> = ContentViewBindingDelegate(layoutRes)