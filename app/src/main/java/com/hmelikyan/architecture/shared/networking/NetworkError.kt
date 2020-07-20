package com.hmelikyan.architecture.shared.networking

import com.hmelikyan.architecture.App
import retrofit2.HttpException
import java.net.HttpURLConnection

class NetworkError(private var mError: Throwable) : Throwable(mError) {
    private val mContext = App.getInstance()
    private lateinit var errorMessage: String

    init {
        isAuthError()
    }

    private fun isAuthError() {
        if (mError is HttpException) {
            //todo add default message
//            errorMessage = mContext.resources.getString(R.string.default_error_message)
            errorMessage = mError.message!!
            if ((mError as HttpException).code() == HttpURLConnection.HTTP_UNAUTHORIZED || (mError as HttpException).code() == HttpURLConnection.HTTP_FORBIDDEN) {
                throw UnauthorizedException(errorMessage)
            }
        } else {
            errorMessage = mError.message!!
        }
    }

    fun getAppErrorMessage(): String {
        return errorMessage
    }
}