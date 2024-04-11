package ru.mystreet.errors.domain

interface ErrorConfigResolver<T : Any> {

    fun resolve(value: T): ErrorInfo

}