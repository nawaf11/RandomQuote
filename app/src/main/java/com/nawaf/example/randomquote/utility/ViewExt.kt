package com.nawaf.example.randomquote.utility

import android.app.Activity
import android.app.AlertDialog
import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * re-usable in other apps
 * */

fun Activity.toast(msg : String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun Fragment.toast(msg : String) {
    activity?.toast(msg)
}
