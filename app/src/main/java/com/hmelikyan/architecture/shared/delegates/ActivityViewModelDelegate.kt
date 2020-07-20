package com.hmelikyan.architecture.shared.delegates

import androidx.lifecycle.ViewModelProviders
import com.hmelikyan.architecture.view.root.BaseActivity
import com.hmelikyan.architecture.view.root.view_model.BaseViewModel

class ActivityViewModelDelegate<T : BaseViewModel>(
    private val viewModelClass: Class<T>,
    private val activity: BaseActivity
) : Lazy<T> {
    private var viewModel: T? = null

    override val value: T
        get():T {
            if (viewModel == null) {
                viewModel = ViewModelProviders.of(activity).get(viewModelClass)
            }
            return viewModel!!
        }

    override fun isInitialized(): Boolean = viewModel != null

}

inline fun <reified T : BaseViewModel> BaseActivity.viewModelProvider():
        Lazy<T> = ActivityViewModelDelegate(T::class.java, this)