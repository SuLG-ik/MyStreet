package ru.mystreet.errors.domain

interface HandleableError {

    val data: ErrorInfo.ErrorData

    val config: ErrorInfo.Config

}
