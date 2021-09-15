package com.adrian.thenews.core.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object Formatter {

    @SuppressLint("SimpleDateFormat")
    fun getDate(date: String): String? {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val yearFormat = SimpleDateFormat("dd-MMMM-yyyy")
        return simpleDateFormat.parse(date)?.let { yearFormat.format(it) }
    }

}