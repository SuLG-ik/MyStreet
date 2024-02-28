package ru.mystreet.core.component

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.subscribe
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.rx.Observer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutorScope
import com.arkivanov.mvikotlin.extensions.coroutines.ExecutorBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.asTimeSource
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

inline fun <reified T : Store<*, *, *>> DIComponentContext.getStore(
    vararg params: Any,
): T {
    return instanceKeeper.getStore { get<T> { parametersOf(*params) } }
}

fun <T : Store<*, State, *>, State : Any> T.values(lifecycleOwner: LifecycleOwner): Value<State> {
    val value = MutableValue(state)
    val disposable = states(ValueObserver(value))
    lifecycleOwner.doOnDestroy(disposable::dispose)
    return value
}

fun <T : Any, E : Any, M : Any> zip(
    lifecycleOwner: LifecycleOwner,
    value1: Value<T>,
    value2: Value<E>,
    transform: (first: T, second: E) -> M,
): Value<M> {
    val target = MutableValue(transform(value1.value, value2.value))
    value1.subscribe(lifecycleOwner.lifecycle) {
        target.value = transform(it, value2.value)
    }
    value2.subscribe(lifecycleOwner.lifecycle) {
        target.value = transform(value1.value, it)
    }
    return target
}

class ValueObserver<T : Any>(
    private val mutableValue: MutableValue<T>,
) : Observer<T> {
    override fun onComplete() {
    }

    override fun onNext(value: T) {
        mutableValue.value = value
    }

}

@OptIn(ExperimentalTime::class)
val timeSource = Clock.System.asTimeSource()

@OptIn(ExperimentalMviKotlinApi::class)
inline fun <reified T : Intent, Intent : Any, Action : Any, State : Any, Message : Any, Label : Any> ExecutorBuilder<Intent, Action, State, Message, Label>.onIntentWithCoolDown(
    time: Duration,
    noinline handler: CoroutineExecutorScope<State, Message, Action, Label>.(intent: T) -> Unit,
) {
    var nextLaunchTime = timeSource.markNow()
    onIntent<T> {
        if (nextLaunchTime.hasPassedNow()) {
            handler(it)
            nextLaunchTime = timeSource.markNow() + time
        }
    }
}

@OptIn(ExperimentalMviKotlinApi::class)
interface DeferredCoroutineExecutorScope<out State : Any, in Message : Any, in Action : Any, in Label : Any> :
    CoroutineExecutorScope<State, Message, Action, Label> {

    fun deferredLaunch(handler: suspend CoroutineScope.() -> Unit)

}

@OptIn(ExperimentalMviKotlinApi::class)
inline fun <reified T : Intent, Intent : Any, Action : Any, State : Any, Message : Any, Label : Any> ExecutorBuilder<Intent, Action, State, Message, Label>.onIntentWithDebounce(
    time: Duration,
    noinline handler: DeferredCoroutineExecutorScope<State, Message, Action, Label>.(intent: T) -> Unit,
) {
    var nextLaunchTime = timeSource.markNow()
    var job: Job? = null
    onIntent<T> {
        val debouncedCoroutineExecutorScope = DeferredCoroutineExecutorScopeImpl(this)
        with(debouncedCoroutineExecutorScope) { handler(it) }
        job?.cancel()
        job = launch {
            if (nextLaunchTime.hasNotPassedNow()) {
                nextLaunchTime = timeSource.markNow() + time
                delay(time)
            } else {
                nextLaunchTime = timeSource.markNow() + time
            }
            debouncedCoroutineExecutorScope.launch()
        }
    }
}

@OptIn(ExperimentalMviKotlinApi::class)
inline fun <reified T : Intent, Intent : Any, Action : Any, State : Any, Message : Any, Label : Any> ExecutorBuilder<Intent, Action, State, Message, Label>.onIntentWithSkipping(
    noinline handler: DeferredCoroutineExecutorScope<State, Message, Action, Label>.(intent: T) -> Unit,
) {
    var job: Job? = null
    onIntent<T> {
        if (job?.isActive == true)
            return@onIntent
        val debouncedCoroutineExecutorScope = DeferredCoroutineExecutorScopeImpl(this)
        with(debouncedCoroutineExecutorScope) { handler(it) }
        job = launch {
            debouncedCoroutineExecutorScope.launch()
        }
    }
}
