package com.hmelikyan.architecture.view.root.viewCommand

import androidx.annotation.StringRes

interface ViewCommand


class NetworkErrorViewCommand : ViewCommand

class ShowMessageViewCommand(@StringRes val resId: Int) : ViewCommand

class ShowMessageTextViewCommand(val errorMessage: String) : ViewCommand

class HideLoadingViewCommand : ViewCommand
class ShowLoadingViewCommand : ViewCommand

class RefreshTokenViewCommand :ViewCommand