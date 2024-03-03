package ru.mystreet.core.time

import kotlinx.datetime.LocalDateTime

interface DateTimeService {

    fun currentDateTime(withTimeZone: Boolean = false): LocalDateTime

}