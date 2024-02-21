package ru.mystreet.core.component

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutorScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

class DebouncedCoroutineExecutorScopeImpl<out State : Any, in Message : Any, in Action : Any, in Label : Any>(
    scope: CoroutineExecutorScope<State, Message, Action, Label>,
) :
    DebouncedCoroutineExecutorScope<State, Message, Action, Label>,
    CoroutineExecutorScope<State, Message, Action, Label> by scope {

    private var handler: (suspend CoroutineScope.() -> Unit)? = null

    override fun debouncedLaunch(handler: suspend CoroutineScope.() -> Unit) {
        this.handler = handler
    }

    suspend fun launch() {
        coroutineScope {
            handler?.invoke(this)
        }
    }


}