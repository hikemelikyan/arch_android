package com.hmelikyan.architecture.shared.extensions

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AppCompatDelegate
import com.hmelikyan.architecture.App

enum class AppMode {
    LIGHT,
    DARK;

    companion object {
        fun get(isDark: Boolean) = if (isDark) DARK else LIGHT
    }
}

fun getAppMode(): AppMode {
    return AppMode.get(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
}

fun createIntentToApplicationPackage(): Intent {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts(
        "package",
        App.getInstance().packageName,
        null
    )
    intent.data = uri
    return intent
}