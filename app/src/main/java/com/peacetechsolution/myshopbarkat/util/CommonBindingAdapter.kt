package com.peacetechsolution.myshopbarkat.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter

/**
 * Created by Sajid.
 */

@BindingAdapter("setImage")
fun setImage(imageView: AppCompatImageView, url: String?) {
    url?.let {
        imageView.setImage(url)
    }
}
