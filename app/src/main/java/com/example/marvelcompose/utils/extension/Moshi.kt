package com.example.marvelcompose.utils.extension

import com.squareup.moshi.Moshi

fun <T> Moshi.toJson(className: Class<T>, obj: T): String {
    return adapter(className).lenient().toJson(obj)
}

fun <T> Moshi.fromJson(className: Class<T>, string: String): T? {
    return adapter(className).lenient().fromJson(string)
}