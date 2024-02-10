package ru.mystreet.core.component

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.essenty.statekeeper.StateKeeperOwner
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer

inline fun <reified T : Any> StateKeeperOwner.savedValue(
    key: String,
    noinline defaultValue: () -> T,
): MutableValue<T> {
    return savedValue(key, defaultValue, serializer<T>())
}

fun <T : Any> StateKeeperOwner.savedValue(
    key: String,
    defaultValue: () -> T,
    serializer: KSerializer<T>
): MutableValue<T> {
    return stateKeeper.savedValue(key, defaultValue, serializer)
}

inline fun <reified T : Any> StateKeeper.savedValue(
    key: String,
    noinline defaultValue: () -> T,
): MutableValue<T> {
    return savedValue(key, defaultValue, serializer<T>())
}

fun <T : Any> StateKeeper.savedValue(
    key: String,
    defaultValue: () -> T,
    serializer: KSerializer<T>
): MutableValue<T> {
    val initialValue = consume(key, serializer) ?: defaultValue()
    val value = MutableValue(initialValue)
    register(key, serializer, value::value)
    return value
}