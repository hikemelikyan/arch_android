package com.hmelikyan.architecture.shared.delegates

import androidx.lifecycle.ViewModelProviders
import com.hmelikyan.architecture.view.root.BaseActivity
import com.hmelikyan.architecture.view.root.BaseBottomSheetFragment
import com.hmelikyan.architecture.view.root.view_model.BaseViewModel

class BottomSheetFragmentViewModelDelegate<T : BaseViewModel>(
    private val viewModelClass: Class<T>,
    private val fragment: BaseBottomSheetFragment
) : Lazy<T> {
    private var viewModel: T? = null

    override val value: T
        get():T {
            if (viewModel == null) {
                viewModel = ViewModelProviders.of(fragment).get(viewModelClass)
            }
            return viewModel!!
        }

    override fun isInitialized(): Boolean = viewModel != null

}

inline fun <reified T : BaseViewModel> BaseBottomSheetFragment.viewModelProvider():
        Lazy<T> = BottomSheetFragmentViewModelDelegate(T::class.java, this)