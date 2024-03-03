package ru.mystreet.core.time

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal class DateTimeServiceImpl(
    private val clock: Clock,
) : DateTimeService {

    private fun timeZone(withTimeZone: Boolean): TimeZone {
        return if (withTimeZone)
            TimeZone.currentSystemDefault()
        else
            TimeZone.UTC
    }

    override fun currentDateTime(withTimeZone: Boolean): LocalDateTime {
        return clock.now().toLocalDateTime(timeZone(withTimeZone))
    }

}