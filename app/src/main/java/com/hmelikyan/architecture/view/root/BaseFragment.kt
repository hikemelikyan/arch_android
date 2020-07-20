package com.hmelikyan.architecture.view.root


import android.annotation.TargetApi
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.fragment.app.Fragment


open class BaseFragment : Fragment(), IBaseView {

    private var baseActivity: BaseActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            this.baseActivity = context
        }
    }

    override fun defaultError() {
        baseActivity?.defaultError()
    }

    override fun onNetworkError() {
        baseActivity?.onNetworkError()
    }

    override fun onError(resId: Int) {
        baseActivity?.onError(resId)
    }

    override fun onError(message: String) {
        baseActivity?.onError(message)
    }

    override fun setLightStatusBar() {
        baseActivity?.setLightStatusBar()
    }

    override fun clearLightStatusBar() {
        baseActivity?.clearLightStatusBar()
    }

    override fun isMainLoading(isLoading: Boolean) {

    }

    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                || baseActivity!!.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    @TargetApi(Build.VERSION_CODES.M)
    open fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }
}
