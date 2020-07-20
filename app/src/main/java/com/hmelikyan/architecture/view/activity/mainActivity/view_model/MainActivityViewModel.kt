package com.hmelikyan.architecture.view.activity.mainActivity.view_model

import android.app.Application
import com.hmelikyan.architecture.view.activity.mainActivity.viewCommand.LoadFragmentViewCommand
import com.hmelikyan.architecture.view.root.view_model.BaseViewModel

class MainActivityViewModel(application: Application) : BaseViewModel(application){

    fun onLoadFragmentClick(){
        _viewCommandsLiveData.postValue(LoadFragmentViewCommand())
    }

}