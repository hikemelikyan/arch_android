package com.hmelikyan.architecture.view.root

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hmelikyan.architecture.view.root.viewCommand.*
import com.hmelikyan.architecture.view.root.view_model.BaseViewModel

abstract class BaseFragmentMVVM<VB : ViewDataBinding, VM : BaseViewModel> : BaseFragment() {


    abstract class BaseFragmentMVVM<VB : ViewDataBinding, VM : BaseViewModel> : BaseFragment() {

        private lateinit var _binding: VB
        protected val mBinding: VB
            get() = _binding

        private lateinit var _viewModel: VM
        protected val mViewModel: VM
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

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
            _binding.lifecycleOwner = this
            linkView()
            return mBinding.root
        }

        protected abstract fun linkView()

        private fun processViewCommandGlobal(viewCommand: ViewCommand?) {
            Log.d("Response", viewCommand.toString())

            when (viewCommand) {
                is NetworkErrorViewCommand -> onNetworkError()
                is ShowMessageViewCommand -> onError(viewCommand.resId)
                is ShowMessageTextViewCommand -> onError(viewCommand.errorMessage)
                is ShowLoadingViewCommand -> isMainLoading(true)
                is HideLoadingViewCommand -> isMainLoading(false)
                else -> processViewCommand(viewCommand)
            }
        }

        protected abstract fun processViewCommand(viewCommand: ViewCommand?)
    }
}