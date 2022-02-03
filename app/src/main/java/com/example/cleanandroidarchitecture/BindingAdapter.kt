package com.example.cleanandroidarchitecture

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:text")
fun setText(view: TextView, num: Int) {
    view.text = num.toString()
}