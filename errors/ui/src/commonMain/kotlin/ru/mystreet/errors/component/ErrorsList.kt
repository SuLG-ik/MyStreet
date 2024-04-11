package ru.mystreet.errors.component

import com.arkivanov.decompose.value.Value
import kotlinx.collections.immutable.ImmutableList
import ru.mystreet.errors.domain.TimedError

interface ErrorsList {

    val items: Value<ImmutableList<TimedError>>

    fun onHideError(error: TimedError)

}