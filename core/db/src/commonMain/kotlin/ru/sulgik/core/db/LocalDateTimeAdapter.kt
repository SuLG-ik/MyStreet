package ru.sulgik.core.db

import app.cash.sqldelight.ColumnAdapter
import kotlinx.datetime.LocalDateTime

object LocalDateTimeAdapter : ColumnAdapter<LocalDateTime, String> {
    override fun decode(databaseValue: String): LocalDateTime {
        return LocalDateTime.parse(databaseValue)
    }

    override fun encode(value: LocalDateTime): String {
        return value.toString()
    }
}