package ru.mystreet.map.mapobject.component.info

import com.arkivanov.decompose.value.Value
import ru.mystreet.map.domain.entity.AddMapObjectReviewField

interface MapObjectReviewAdd {

    val field: Value<AddMapObjectReviewField>

    fun onContinue()

    fun onBack()

    fun onTitleInput(value: String)

    fun onContentInput(value: String)

    fun onRatingInput(value: Int)

    fun onAuthorHiddenInput(value: Boolean)

    val isLoading: Value<Boolean>
    val isContinueAvailable: Value<Boolean>
}