package ru.mystreet.errors.store

import com.arkivanov.mvikotlin.core.store.Executor
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutorScope
import com.arkivanov.mvikotlin.extensions.coroutines.ExecutorBuilder
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import ru.mystreet.core.component.ContextMergingCoroutineExecutorScope
import ru.mystreet.core.component.onIntentSkipping
import ru.mystreet.errors.domain.MutableErrorDispatcher
import kotlin.coroutines.CoroutineContext

private class ErrorDispatcherCoroutineExceptionHandler(
    private val dispatcher: MutableErrorDispatcher,
) : CoroutineExceptionHandler {
    override val key: CoroutineContext.Key<*> = CoroutineExceptionHandler
    override fun handleException(context: CoroutineContext, exception: Throwable) {
        dispatcher.dispatch(exception)
    }
}

fun MutableErrorDispatcher.asCoroutineExceptionHandler(): CoroutineExceptionHandler {
    return ErrorDispatcherCoroutineExceptionHandler(this)
}

@ExperimentalMviKotlinApi
fun <Intent : Any, Action : Any, State : Any, Message : Any, Label : Any> safeCoroutineExecutorFactory(
    mainContext: CoroutineContext = Dispatchers.Main,
    dispatcher: MutableErrorDispatcher,
    block: ExecutorBuilder<Intent, Action, State, Message, Label>.() -> Unit,
): () -> Executor<Intent, Action, State, Message, Label> = coroutineExecutorFactory(
    mainContext = mainContext + dispatcher.asCoroutineExceptionHandler(), block = block
)


@OptIn(ExperimentalMviKotlinApi::class)
inline fun <reified T : Intent, Intent : Any, Action : Any, State : Any, Message : Any, Label : Any> ExecutorBuilder<Intent, Action, State, Message, Label>.onIntentSafe(
    dispatcher: MutableErrorDispatcher,
    noinline handler: CoroutineExecutorScope<State, Message, Action, Label>.(intent: T) -> Unit,
) {
    onIntent<T>(safeIntentHandler(dispatcher, handler))
}

@OptIn(ExperimentalMviKotlinApi::class)
inline fun <reified T : Action, Intent : Any, Action : Any, State : Any, Message : Any, Label : Any> ExecutorBuilder<Intent, Action, State, Message, Label>.onActionSafe(
    dispatcher: MutableErrorDispatcher,
    noinline handler: CoroutineExecutorScope<State, Message, Action, Label>.(intent: T) -> Unit,
) {
    onAction<T>(safeIntentHandler(dispatcher, handler))
}


@OptIn(ExperimentalMviKotlinApi::class)
inline fun <reified T : Intent, Intent : Any, Action : Any, State : Any, Message : Any, Label : Any> ExecutorBuilder<Intent, Action, State, Message, Label>.onIntentSafeSkipping(
    dispatcher: MutableErrorDispatcher,
    noinline handler: CoroutineExecutorScope<State, Message, Action, Label>.(intent: T) -> Unit,
) {
    onIntentSkipping(safeIntentHandler(dispatcher, handler))
}


@OptIn(ExperimentalMviKotlinApi::class)
private class SafeIntentHandler<T : Intent, Intent : Any, Action : Any, State : Any, Message : Any, Label : Any>(
    private val handler: CoroutineExecutorScope<State, Message, Action, Label>.(intent: T) -> Unit,
    private val dispatcher: MutableErrorDispatcher,
) : (CoroutineExecutorScope<State, Message, Action, Label>, T) -> Unit {
    val exceptionHandler = dispatcher.asCoroutineExceptionHandler()
    override fun invoke(p1: CoroutineExecutorScope<State, Message, Action, Label>, p2: T) {
        val newScope = ContextMergingCoroutineExecutorScope(
            scope = p1,
            context = exceptionHandler,
        )
        try {
            handler.invoke(newScope, p2)
        } catch (e: Exception) {
            dispatcher.dispatch(e)
        }
    }
}

@OptIn(ExperimentalMviKotlinApi::class)
fun <T : Intent, Intent : Any, Action : Any, State : Any, Message : Any, Label : Any> safeIntentHandler(
    dispatcher: MutableErrorDispatcher,
    handler: CoroutineExecutorScope<State, Message, Action, Label>.(intent: T) -> Unit,
): CoroutineExecutorScope<State, Message, Action, Label>.(intent: T) -> Unit {
    return SafeIntentHandler(handler, dispatcher)
}