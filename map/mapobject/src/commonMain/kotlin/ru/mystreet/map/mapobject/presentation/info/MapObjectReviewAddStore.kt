package ru.mystreet.map.mapobject.presentation.info

import com.arkivanov.mvikotlin.core.store.Store
import ru.mystreet.map.domain.entity.AddMapObjectReviewField

interface MapObjectReviewAddStore :
    Store<MapObjectReviewAddStore.Intent, MapObjectReviewAddStore.State, MapObjectReviewAddStore.Label> {

    data class SavedState(
        val title: String,
        val content: String,
    )

    sealed interface Intent {
        data class TitleInput(val value: String) : Intent
        data class ContentInput(val value: String) : Intent
        object Continue : Intent
    }

    data class State(
        val isLoading: Boolean,
        val field: AddMapObjectReviewField,
    )

    sealed interface Label

}