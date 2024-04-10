package ru.mystreet.errors.domain

import kotlinx.datetime.LocalDateTime
import ru.mystreet.errors.domain.ErrorInfo

data class TimedError(
    val info: ErrorInfo,
    val startTime: LocalDateTime,
)