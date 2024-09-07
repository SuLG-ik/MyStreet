package ru.mystreet.map.component.add

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.mystreet.core.component.AppComponentContext
import ru.mystreet.core.component.DIComponentContext
import ru.mystreet.core.component.getSavedStateStore
import ru.mystreet.core.component.values
import ru.mystreet.map.domain.entity.AddMapObjectField
import ru.mystreet.map.presentation.add.EditMapNewObjectLoadingStore

class EditMapNewObjectLoadingComponent(
    componentContext: DIComponentContext,
    field: AddMapObjectField,
    onContinue: () -> Unit,
) : AppComponentContext(componentContext), EditMapNewObjectLoading {

    private val coroutineScope = coroutineScope()

    private val store: EditMapNewObjectLoadingStore = getSavedStateStore(
        key = "edit_map_adding_loading",
        initialSavedState = { EditMapNewObjectLoadingStore.SavedState(field) },
    )

    override val isLoading: Value<Boolean> = store.values(this).map { it.isLoading }

    init {
        store.labels.onEach {
            when(it) {
                EditMapNewObjectLoadingStore.Label.LoadingCompleted -> onContinue()
                EditMapNewObjectLoadingStore.Label.Canceled -> onContinue()
            }
        }.flowOn(Dispatchers.Main).launchIn(coroutineScope)
    }

}