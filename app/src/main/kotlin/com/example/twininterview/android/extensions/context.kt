package com.example.twininterview.android.extensions

import android.content.Context
import java.io.IOException
import java.io.InputStream

fun Context.loadJSONFromAsset(fileName: String): Pair<String?, Throwable?> {
    val jsonString: String
    try {
        jsonString = assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return Pair(null, ioException)
    }
    return Pair(jsonString, null)
}