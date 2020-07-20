package com.hmelikyan.architecture.view.fragments.firstFragment

import com.hmelikyan.architecture.R
import com.hmelikyan.architecture.databinding.FragmentFirstTestBinding
import com.hmelikyan.architecture.view.fragments.firstFragment.view_model.FirstTestViewModel
import com.hmelikyan.architecture.view.root.BaseFragmentMVVM
import com.hmelikyan.architecture.view.root.viewCommand.ViewCommand

class FirstTestFragment : BaseFragmentMVVM<FragmentFirstTestBinding, FirstTestViewModel>() {
    override val layoutResId: Int
        get() = R.layout.fragment_first_test
    override val viewModelType: Class<FirstTestViewModel>
        get() = FirstTestViewModel::class.java

    override fun linkView() {
        mBinding.viewModel = mViewModel
    }

    override fun processViewCommand(viewCommand: ViewCommand?) {

    }

}