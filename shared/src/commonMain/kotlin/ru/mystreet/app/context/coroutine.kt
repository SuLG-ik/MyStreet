package ru.mystreet.app.context

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

fun LifecycleOwner.coroutineScope(): CoroutineScope {
    return MainScope().bind(lifecycle)
}

private fun CoroutineScope.bind(lifecycle: Lifecycle): CoroutineScope {
    lifecycle.doOnDestroy { cancel() }
    return this
}