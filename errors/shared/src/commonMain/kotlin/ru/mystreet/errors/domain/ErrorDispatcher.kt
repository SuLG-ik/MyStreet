package ru.mystreet.errors.domain

import kotlinx.coroutines.flow.SharedFlow

interface ErrorDispatcher {
    fun dispatch(error: Any)
}

interface MutableErrorDispatcher : ErrorDispatcher {

    val errors: SharedFlow<ErrorInfo>

}