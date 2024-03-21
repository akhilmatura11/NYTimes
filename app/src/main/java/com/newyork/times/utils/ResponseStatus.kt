package com.newyork.times.utils

sealed class ResponseStatus(val message: String? = null) {
    class Loading : ResponseStatus()
    class Error(message: String) : ResponseStatus(message)
    class Success : ResponseStatus()
}