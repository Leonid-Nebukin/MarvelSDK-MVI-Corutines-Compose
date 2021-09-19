package com.example.marvelcompose.utils.extension

import java.math.BigInteger
import java.security.MessageDigest

fun String.MD5(input: String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}