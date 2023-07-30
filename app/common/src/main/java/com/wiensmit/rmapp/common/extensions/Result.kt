package com.wiensmit.rmapp.common.extensions

fun <T> Result.Companion.safeRun(block: () -> T): Result<T> = try {
    success(block())
} catch (e: Throwable) {
    failure(e)
}

suspend fun <T> Result.Companion.safeRunAsync(block: suspend () -> T): Result<T> = try {
    success(block())
} catch (e: Throwable) {
    failure(e)
}