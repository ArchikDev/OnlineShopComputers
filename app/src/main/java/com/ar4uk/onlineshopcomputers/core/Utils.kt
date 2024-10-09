package com.ar4uk.onlineshopcomputers.core

import android.content.Context
import android.util.Log
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import com.ar4uk.onlineshopcomputers.core.Constants.TAG

fun printError(e: Exception?) = e?.apply {
    Log.e(TAG, "$message")
}

fun toastMessage(
    context: Context,
    message: String
) = makeText(context, message, LENGTH_LONG).show()