package com.wiensmit.rmapp.common.extensions

fun List<String>?.nullIfEmpty(): List<String>? {
    return if (isNullOrEmpty()) {
        null
    } else {
        this
    }
}