package com.hmelikyan.architecture.shared.extensions

import android.text.SpannableString
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import com.hmelikyan.architecture.R

fun SpannableString.markText(
    text: String,
    color: Int = getColor(R.color.colorAccent)
): SpannableString {
    this.setSpan(
        ForegroundColorSpan(color),
        this.indexOf(text),
        this.indexOf(text) + text.length,
        SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return this
}

fun String.markText(text: String, color: Int = getColor(R.color.colorAccent)): SpannableString {
    val span = SpannableString(this)
    span.setSpan(
        ForegroundColorSpan(color),
        span.indexOf(text),
        span.indexOf(text) + text.length,
        SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return span
}

fun CharSequence.markText(
    text: String,
    color: Int = getColor(R.color.colorAccent),
    clickAction: (() -> Unit)? = null
): SpannableString {
    val span = SpannableString(this)
    span.setSpan(
        ForegroundColorSpan(color),
        span.indexOf(text),
        span.indexOf(text) + text.length,
        SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    clickAction?.let {
        span.setSpan(
            object : ClickableSpan() {
                override fun onClick(widget: View) {
                    it()
                }
            },
            span.indexOf(text),
            span.indexOf(text) + text.length,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    return span
}