package ru.mystreet.errors.domain

import kotlin.reflect.KClass

data class ErrorHandler<T : Any>(
    val type: KClass<T>,
    val resolver: ErrorConfigResolver<T>,
)

typealias ErrorHandlerFactory <T> = (UniqueIdGenerator) -> ErrorHandler<T>

inline fun <reified T : Any> ErrorHandler(resolver: ErrorConfigResolver<T>): ErrorHandler<T> {
    return ErrorHandler(T::class, resolver)
}

inline fun <reified T : Any>  ErrorConfigResolver<T>.asHandler(): ErrorHandler<T> {
    return ErrorHandler(this)
}