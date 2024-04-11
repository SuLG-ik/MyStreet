package ru.mystreet.errors.store

import com.arkivanov.mvikotlin.core.store.Store
import kotlinx.collections.immutable.PersistentList
import ru.mystreet.errors.domain.ErrorInfo
import ru.mystreet.errors.domain.TimedError

interface ErrorsListStore :
    Store<ErrorsListStore.Intent, ErrorsListStore.State, ErrorsListStore.Label> {

    sealed interface Intent {
        data class HideError(val error: TimedError): Intent
    }

    data class State(
        val errorsQueue: PersistentList<ErrorInfo>,
        val visibleErrors: PersistentList<TimedError>,
    )

    sealed interface Label

}