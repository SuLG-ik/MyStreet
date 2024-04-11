package ru.mystreet.errors.store

import com.arkivanov.mvikotlin.core.store.Executor
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutorScope
import com.arkivanov.mvikotlin.extensions.coroutines.ExecutorBuilder
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import ru.mystreet.core.component.ContextMergingCoroutineExecutorScope
import ru.mystreet.core.component.DeferredCoroutineExecutorScope
import ru.mystreet.core.component.debounceIntentHandler
import ru.mystreet.core.component.onIntentSkipping
import ru.mystreet.errors.domain.ErrorDispatcher
import kotlin.coroutines.CoroutineContext
import kotlin.time.Duration

private class ErrorDispatcherCoroutineExceptionHandler(
    private val dispatcher: ErrorDispatcher,
) : CoroutineExceptionHandler {
    override val key: CoroutineContext.Key<*> = CoroutineExceptionHandler
    override fun handleException(context: CoroutineContext, exception: Throwable) {
        dispatcher.dispatch(exception)
    }
}

fun ErrorDispatcher.asCoroutineExceptionHandler(): CoroutineExceptionHandler {
    return ErrorDispatcherCoroutineExceptionHandler(this)
}

@ExperimentalMviKotlinApi
fun <Intent : Any, Action : Any, State : Any, Message : Any, Label : Any> safeCoroutineExecutorFactory(
    mainContext: CoroutineContext = Dispatchers.Main,
    dispatcher: ErrorDispatcher,
    block: ExecutorBuilder<Intent, Action, State, Message, Label>.() -> Unit,
): () -> Executor<Intent, Action, State, Message, Label> = coroutineExecutorFactory(
    mainContext = mainContext + dispatcher.asCoroutineExceptionHandler(), block = block
)

@OptIn(ExperimentalMviKotlinApi::class)
inline fun <reified T : Intent, Intent : Any, Action : Any, State : Any, Message : Any, Label : Any> ExecutorBuilder<Intent, Action, State, Message, Label>.onIntentSafeDebounce(
    duration: Duration,
    dispatcher: ErrorDispatcher,
    noinline handler: DeferredCoroutineExecutorScope<State, Message, Action, Label>.(intent: T) -> Unit,
) {
    onIntent<T>(safeHandler(dispatcher, debounceIntentHandler(duration, handler)))
}


@OptIn(ExperimentalMviKotlinApi::class)
inline fun <reified T : Intent, Intent : Any, Action : Any, State : Any, Message : Any, Label : Any> ExecutorBuilder<Intent, Action, State, Message, Label>.onIntentSafe(
    dispatcher: ErrorDispatcher,
    noinline handler: CoroutineExecutorScope<State, Message, Action, Label>.(intent: T) -> Unit,
) {
    onIntent<T>(safeHandler(dispatcher, handler))
}

@OptIn(ExperimentalMviKotlinApi::class)
inline fun <reified T : Action, Intent : Any, Action : Any, State : Any, Message : Any, Label : Any> ExecutorBuilder<Intent, Action, State, Message, Label>.onActionSafe(
    dispatcher: ErrorDispatcher,
    noinline handler: CoroutineExecutorScope<State, Message, Action, Label>.(intent: T) -> Unit,
) {
    onAction<T>(safeHandler(dispatcher, handler))
}


@OptIn(ExperimentalMviKotlinApi::class)
inline fun <reified T : Intent, Intent : Any, Action : Any, State : Any, Message : Any, Label : Any> ExecutorBuilder<Intent, Action, State, Message, Label>.onIntentSafeSkipping(
    dispatcher: ErrorDispatcher,
    noinline handler: CoroutineExecutorScope<State, Message, Action, Label>.(intent: T) -> Unit,
) {
    onIntentSkipping(safeHandler(dispatcher, handler))
}


@OptIn(ExperimentalMviKotlinApi::class)
private class SafeIntentHandler<T : Intent, Intent : Any, Action : Any, State : Any, Message : Any, Label : Any>(
    private val handler: CoroutineExecutorScope<State, Message, Action, Label>.(intent: T) -> Unit,
    private val dispatcher: ErrorDispatcher,
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
fun <T : Intent, Intent : Any, Action : Any, State : Any, Message : Any, Label : Any> safeHandler(
    dispatcher: ErrorDispatcher,
    handler: CoroutineExecutorScope<State, Message, Action, Label>.(intent: T) -> Unit,
): CoroutineExecutorScope<State, Message, Action, Label>.(intent: T) -> Unit {
    return SafeIntentHandler(handler, dispatcher)
}