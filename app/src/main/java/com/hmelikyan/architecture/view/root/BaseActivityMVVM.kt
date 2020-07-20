package com.hmelikyan.architecture.view.root

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hmelikyan.architecture.view.root.viewCommand.*
import com.hmelikyan.architecture.view.root.view_model.BaseViewModel

abstract class BaseActivityMVVM<VB : ViewDataBinding, VM : BaseViewModel> : BaseActivity() {

    private lateinit var _binding: VB
    protected val mBinding:VB
        get() = _binding

    private lateinit var _viewModel: VM
    protected val mViewModel:VM
        get() = _viewModel

    protected abstract val layoutResId: Int

    protected abstract val viewModelType: Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModel = ViewModelProviders.of(this).get(viewModelType)
        _viewModel.viewCommands.observe(this, Observer {
            processViewCommandGlobal(it)
        })
    }

    private fun processViewCommandGlobal(viewCommand: ViewCommand?) {
        when (viewCommand) {
            is NetworkErrorViewCommand -> onNetworkError()
            is ShowMessageViewCommand -> onError(viewCommand.resId)
            is ShowMessageTextViewCommand -> onError(viewCommand.errorMessage)
            is ShowLoadingViewCommand -> isMainLoading(viewCommand.isLoading)
            else -> processViewCommand(viewCommand)
        }
    }

    protected abstract fun processViewCommand(viewCommand: ViewCommand?)

}