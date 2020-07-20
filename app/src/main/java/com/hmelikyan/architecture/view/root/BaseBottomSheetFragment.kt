package com.hmelikyan.architecture.view.root

import android.content.Context
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.hmelikyan.architecture.shared.extensions.getColor
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.hmelikyan.architecture.R

open class BaseBottomSheetFragment : BottomSheetDialogFragment(), IBaseView {
    private var mBaseActivity: BaseActivity? = null
    private var rootView: CoordinatorLayout? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity)
            mBaseActivity = context
    }

    protected fun onCreateView(dialog: View): View? {
        rootView = CoordinatorLayout(requireContext())
        rootView?.addView(dialog)
        return rootView
    }

    override fun defaultError() {
        showSnackBar(resources.getString(R.string.default_error_message))
    }

    override fun onNetworkError() {
        showSnackBar(resources.getString(R.string.network_error_message))
    }

    override fun onError(resId: Int) {
        onError(getString(resId))
    }

    override fun onError(message: String) {
        showSnackBar(message)
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(rootView!!, message, Snackbar.LENGTH_SHORT)
            .setTextColor(getColor(android.R.color.white))
            .show()
    }

    override fun setLightStatusBar() {
        mBaseActivity?.setLightStatusBar()
    }

    override fun clearLightStatusBar() {
        mBaseActivity?.clearLightStatusBar()
    }

    override fun isMainLoading(isLoading: Boolean) {

    }

}