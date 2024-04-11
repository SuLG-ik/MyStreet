package ru.mystreet.errors.component

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import kotlinx.collections.immutable.ImmutableList
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.getStore
import ru.mystreet.core.component.values
import ru.mystreet.errors.domain.TimedError
import ru.mystreet.errors.store.ErrorsListStore

class ErrorsListComponent(
    componentContext: DIComponentContext,
) : AppComponentContext(componentContext), ErrorsList {

    private val store = getStore<ErrorsListStore>()

    private val state = store.values(this)

    override val items: Value<ImmutableList<TimedError>> = state.map { it.visibleErrors }

    override fun onHideError(error: TimedError) {
        store.accept(ErrorsListStore.Intent.HideError(error))
    }

}