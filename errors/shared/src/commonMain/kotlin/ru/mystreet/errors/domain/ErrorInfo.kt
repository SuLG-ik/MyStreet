package ru.mystreet.errors.domain

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

data class ErrorInfo(
    val uniqueId: Long,
    val data: ErrorData,
    val config: Config,
    val parent: Any,
) {
    sealed class ErrorData {
        data class Raw(
            val title: String,
            val content: String? = null,
            val icon: ImageVector? = null,
            val color: Color? = null,
        ) : ErrorData()
    }

    data class Config(
        val key: String,
        val isShownMultiple: Boolean = true,
        var isHighPriority: Boolean = false,
        val duration: Duration = 3.seconds,
    )
}