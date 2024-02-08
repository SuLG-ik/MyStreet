package ru.mystreet.core.component

import kotlin.jvm.JvmInline

/**
 * Use for Value<T> where T: Any
 */
@JvmInline
value class ValueContainer <T> (val value: T)