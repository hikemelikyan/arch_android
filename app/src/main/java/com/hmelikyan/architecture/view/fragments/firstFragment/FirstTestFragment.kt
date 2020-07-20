package com.hmelikyan.architecture.view.fragments.firstFragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.hmelikyan.architecture.R
import com.hmelikyan.architecture.databinding.FragmentFirstTestBinding
import com.hmelikyan.architecture.view.adapter.RecyclerAdapter
import com.hmelikyan.architecture.view.fragments.firstFragment.viewCommand.NavigateToDetails
import com.hmelikyan.architecture.view.fragments.firstFragment.viewCommand.UpdateList
import com.hmelikyan.architecture.view.fragments.firstFragment.view_model.FirstTestViewModel
import com.hmelikyan.architecture.view.root.BaseFragmentMVVM
import com.hmelikyan.architecture.view.root.viewCommand.ViewCommand

class FirstTestFragment : BaseFragmentMVVM<FragmentFirstTestBinding, FirstTestViewModel>() {
    private lateinit var recyclerAdapter: RecyclerAdapter

    override val layoutResId: Int
        get() = R.layout.fragment_first_test
    override val viewModelType: Class<FirstTestViewModel>
        get() = FirstTestViewModel::class.java

    override fun linkView() {
        mBinding.viewModel = mViewModel
        initView()
    }

    private fun initView() {
        mBinding.cardsRecycler.layoutManager = LinearLayoutManager(requireContext())
        recyclerAdapter = RecyclerAdapter()
        mBinding.cardsRecycler.adapter = recyclerAdapter
    }

    override fun processViewCommand(viewCommand: ViewCommand?) {
        when (viewCommand) {
            is NavigateToDetails -> {
                onError("Navigate To Details")
            }
            is UpdateList -> {
                recyclerAdapter.submitList(viewCommand.list)
            }
            else -> {

            }
        }
    }

}