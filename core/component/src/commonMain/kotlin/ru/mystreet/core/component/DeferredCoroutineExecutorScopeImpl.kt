package ru.mystreet.core.component

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutorScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

class DeferredCoroutineExecutorScopeImpl<out State : Any, in Message : Any, in Action : Any, in Label : Any>(
    scope: CoroutineExecutorScope<State, Message, Action, Label>,
) :
    DeferredCoroutineExecutorScope<State, Message, Action, Label>,
    CoroutineExecutorScope<State, Message, Action, Label> by scope {

    private var handler: (suspend CoroutineScope.() -> Unit)? = null

    override fun deferredLaunch(handler: suspend CoroutineScope.() -> Unit) {
        this.handler = handler
    }

    suspend fun launch() {
        coroutineScope {
            handler?.invoke(this)
        }
    }


}