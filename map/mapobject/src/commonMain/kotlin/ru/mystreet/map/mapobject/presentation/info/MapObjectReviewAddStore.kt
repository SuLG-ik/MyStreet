package ru.mystreet.map.mapobject.presentation.info

import kotlinx.serialization.Serializable
import ru.mystreet.core.component.SavedStateStore
import ru.mystreet.map.domain.entity.AddMapObjectReviewField

interface MapObjectReviewAddStore :
    SavedStateStore<MapObjectReviewAddStore.Intent, MapObjectReviewAddStore.State, MapObjectReviewAddStore.Label, MapObjectReviewAddStore.SavedState> {

    @Serializable
    data class SavedState(
        val mapObjectId: Long,
        val title: String = "",
        val content: String = "",
        val isAuthorHidden: Boolean = false,
        val rating: Int = 0,
    )

    sealed interface Intent {
        data class TitleInput(val value: String) : Intent
        data class ContentInput(val value: String) : Intent
        data class RatingInput(val value: Int) : Intent
        data class AuthorHiddenInput(val value: Boolean) : Intent
        object Continue : Intent
    }

    data class State(
        val isLoading: Boolean,
        val isContinueAvailable: Boolean,
        val field: AddMapObjectReviewField,
    )

    sealed interface Label {
        data object Complete : Label
    }

}