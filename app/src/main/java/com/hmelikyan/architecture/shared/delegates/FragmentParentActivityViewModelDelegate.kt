package com.hmelikyan.architecture.shared.delegates

import androidx.lifecycle.ViewModelProviders
import com.hmelikyan.architecture.view.root.BaseActivity
import com.hmelikyan.architecture.view.root.BaseFragment
import com.hmelikyan.architecture.view.root.view_model.BaseViewModel

class FragmentParentActivityViewModelDelegate<T : BaseViewModel>(
    private val viewModelClass: Class<T>,
    private val fragment: BaseFragment
) : Lazy<T> {
    private var viewModel: T? = null

    override val value: T
        get():T {
            if (viewModel == null) {
                viewModel = ViewModelProviders.of(fragment.requireActivity()).get(viewModelClass)
            }
            return viewModel!!
        }

    override fun isInitialized(): Boolean = viewModel != null

}

inline fun <reified T : BaseViewModel> BaseFragment.activityViewModelProvider(): Lazy<T> = FragmentParentActivityViewModelDelegate(T::class.java, this)