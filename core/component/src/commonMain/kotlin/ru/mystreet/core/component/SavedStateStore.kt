package ru.mystreet.core.component

import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

interface SavedStateStore<Intent : Any, State : Any, Label : Any, SavedState : Any> :
    Store<Intent, State, Label> {

    fun getSavedState(): SavedState

}

inline fun <reified T : SavedStateStore<*, *, *, SavedState>, reified SavedState : Any> DIComponentContext.getSavedStateStore(
    key: String,
    noinline initialSavedState: () -> SavedState,
    vararg params: Any,
): T {
    return getSavedStateStore(key, initialSavedState, serializer<SavedState>(), params)
}

inline fun <reified T : SavedStateStore<*, *, *, SavedState>, SavedState : Any> DIComponentContext.getSavedStateStore(
    key: String,
    noinline initialSavedState: () -> SavedState,
    serializer: KSerializer<SavedState>,
    vararg params: Any,
): T {
    return instanceKeeper.getStore {
        get<T> {
            parametersOf(
                consumeSavedState(
                    key = key,
                    serializer = serializer,
                    initialSavedState = initialSavedState,
                    stateKeeper = stateKeeper
                ), *params
            )
        }
    }.registerSavedState(key, serializer, stateKeeper)
}

fun <SavedState : Any> consumeSavedState(
    key: String,
    serializer: KSerializer<SavedState>,
    initialSavedState: () -> SavedState,
    stateKeeper: StateKeeper,
): SavedState {
    return stateKeeper.consume(key, serializer) ?: initialSavedState()
}

inline fun <T : SavedStateStore<*, *, *, SavedState>, SavedState : Any> T.registerSavedState(
    key: String,
    serializer: KSerializer<SavedState>,
    stateKeeper: StateKeeper,
): T {
    stateKeeper.register(key, serializer) { getSavedState() }
    return this
}