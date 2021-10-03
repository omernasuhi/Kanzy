package com.kanzy.music.extension

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Activity.toast(message: String? = null) {
    Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show()
}

fun Activity.toastLong(message: String? = null) {
    Toast.makeText(this, message.toString(), Toast.LENGTH_LONG).show()
}

fun Fragment.toast(message: String? = null) {
    Toast.makeText(context, message.toString(), Toast.LENGTH_SHORT).show()
}

fun Fragment.toastLong(message: String? = null) {
    Toast.makeText(context, message.toString(), Toast.LENGTH_LONG).show()
}