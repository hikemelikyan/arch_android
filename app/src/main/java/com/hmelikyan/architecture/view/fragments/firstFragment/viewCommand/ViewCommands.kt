package com.hmelikyan.architecture.view.fragments.firstFragment.viewCommand

import com.hmelikyan.architecture.model.CommentModel
import com.hmelikyan.architecture.view.root.viewCommand.ViewCommand

class NavigateToDetails(val postId:Int):ViewCommand

class UpdateList(val list:List<CommentModel>):ViewCommand