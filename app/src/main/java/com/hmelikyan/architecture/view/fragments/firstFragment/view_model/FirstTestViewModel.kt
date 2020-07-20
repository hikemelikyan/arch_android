package com.hmelikyan.architecture.view.fragments.firstFragment.view_model

import android.app.Application
import com.hmelikyan.architecture.shared.data.FirstService
import com.hmelikyan.architecture.view.fragments.firstFragment.viewCommand.UpdateList
import com.hmelikyan.architecture.view.root.view_model.BaseViewModel
import kotlinx.coroutines.launch

class FirstTestViewModel(
    application: Application
) : BaseViewModel(application) {

    private val mService:FirstService = FirstService.getInstance()

    fun onIdFirstClick() {
        launch {
            handle(mService.getPosts(1)){
                _viewCommandsLiveData.postValue(UpdateList(it!!))
            }
        }
    }

    fun onIdSecondClick() {
        launch {
            handle(mService.getPosts(1)){
                _viewCommandsLiveData.postValue(UpdateList(it!!))
            }
        }
    }

}