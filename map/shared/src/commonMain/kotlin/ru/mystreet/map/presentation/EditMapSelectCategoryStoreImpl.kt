package ru.mystreet.map.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import ru.mystreet.map.domain.entity.SelectableCategory
import ru.mystreet.map.domain.entity.MapObjectCategory

@OptIn(ExperimentalMviKotlinApi::class)
class EditMapSelectCategoryStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    savedState: EditMapSelectCategoryStore.SavedState,
) : EditMapSelectCategoryStore,
    Store<EditMapSelectCategoryStore.Intent, EditMapSelectCategoryStore.State, EditMapSelectCategoryStore.Label> by storeFactory.create<_, Action, Message, _, _>(
        name = "EditMapSelectCategoryStoreImpl",
        initialState = EditMapSelectCategoryStore.State(
            defaultSelectableCategories(savedState.selectedCategoryType),
            savedState.selectedCategoryType
        ),
        reducer = {
            when (it) {
                is Message.SetSelectedCategories -> copy(
                    selectedCategory = it.selectedCategory,
                    categories = it.categories,
                )
            }
        },
        bootstrapper = coroutineBootstrapper(coroutineDispatcher) { dispatch(Action.Setup) },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onAction<Action.Setup> {

            }
            onIntent<EditMapSelectCategoryStore.Intent.SelectCategory> { intent ->
                val newCategories =
                    state().categories.map { it.copy(isSelected = it.type == intent.type) }
                dispatch(Message.SetSelectedCategories(newCategories, intent.type))
            }
        },
    ) {

    sealed interface Action {
        data object Setup : Action
    }

    sealed interface Message {
        data class SetSelectedCategories(
            val categories: List<SelectableCategory>,
            val selectedCategory: MapObjectCategory,
        ) : Message
    }

    override fun getSavedState(): EditMapSelectCategoryStore.SavedState {
        return EditMapSelectCategoryStore.SavedState(state.selectedCategory)
    }
}

private fun defaultSelectableCategories(selectedCategory: MapObjectCategory): List<SelectableCategory> {
    return MapObjectCategory.entries.mapIndexed { index, type ->
        SelectableCategory(
            isSelected = type == selectedCategory,
            position = index + 1,
            type = type,
        )
    }
}
