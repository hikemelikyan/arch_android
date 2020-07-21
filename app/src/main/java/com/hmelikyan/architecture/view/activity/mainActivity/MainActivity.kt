package com.hmelikyan.architecture.view.activity.mainActivity

import android.view.View
import com.hmelikyan.architecture.R
import com.hmelikyan.architecture.databinding.ActivityMainBinding
import com.hmelikyan.architecture.view.activity.mainActivity.viewCommand.LoadFragmentViewCommand
import com.hmelikyan.architecture.view.activity.mainActivity.view_model.MainActivityViewModel
import com.hmelikyan.architecture.view.fragments.firstFragment.FirstTestFragment
import com.hmelikyan.architecture.view.root.BaseActivityMVVM
import com.hmelikyan.architecture.view.root.viewCommand.ViewCommand

class MainActivity : BaseActivityMVVM<ActivityMainBinding, MainActivityViewModel>() {

    override val layoutResId: Int
        get() = R.layout.activity_main
    override val viewModelType: Class<MainActivityViewModel>
        get() = MainActivityViewModel::class.java

    override fun processViewCommand(viewCommand: ViewCommand?) {
        if (viewCommand is LoadFragmentViewCommand) {
            mBinding.loadFragmentButton.visibility = View.GONE
            loadFragment()
        }
    }

    private fun loadFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(mBinding.fragmentContainer.id, FirstTestFragment(), "TAG")
        fragmentTransaction.commit()
    }

    override fun linkView() {
        mBinding.viewModel = mViewModel
    }

}