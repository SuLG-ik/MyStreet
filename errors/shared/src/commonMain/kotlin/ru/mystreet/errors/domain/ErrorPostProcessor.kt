package ru.mystreet.errors.domain

interface ErrorPostProcessor {

    fun process(error: ErrorInfo)

}