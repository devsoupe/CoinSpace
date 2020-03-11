package com.perelandrax.coinspace.utilities

import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity

object ActivityUtil {

  fun scanForAppCompatActivity(context: Context): AppCompatActivity? = when (context) {
    is AppCompatActivity -> context
    is ContextWrapper -> scanForAppCompatActivity(context.baseContext)
    else -> null
  }
}