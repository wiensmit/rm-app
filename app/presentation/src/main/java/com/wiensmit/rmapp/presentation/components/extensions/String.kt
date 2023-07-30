package com.wiensmit.rmapp.presentation.components.extensions

fun String.setImageUrlSize(size: Int): String {
    return replace("=s0", "=s$size")
}