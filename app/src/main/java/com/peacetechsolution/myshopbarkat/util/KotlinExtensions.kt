package com.peacetechsolution.myshopbarkat.util

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.peacetechsolution.myshopbarkat.R
import java.util.*

/**
 * Created by Sajid.
 */

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun String.isEmailValid(): Boolean = doesStringMatchPattern(this, Constant.REGEX_EMAIL)

fun String.isPasswordValid(): Boolean =
    doesStringMatchPattern(this, Constant.PASSWORD_PATTERN_WITH_ONE_SPECIAL_CHARS)

fun String.firstLetterCap(): String = this.substring(0, 1).uppercase(Locale.ROOT) + this.substring(1)
    .lowercase(
        Locale.ROOT
    )

fun AppCompatImageView.setImage(url: String, isRound: Boolean = false) {
    if (isRound) {
        Glide.with(this.context)
            .load(url)
            .circleCrop()
            .placeholder(R.drawable.image_placeholder)
            .into(this)
    } else {
        Glide.with(this.context)
            .load(url)
            .placeholder(R.drawable.image_placeholder)
            .into(this)
    }
}
