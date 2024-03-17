package ru.mystreet.account.domain.entity

import ru.mystreet.uikit.ValidatedField

data class LoginField(
    val login: ValidatedField<FieldError>,
    val password: ValidatedField<FieldError>,
) {

    sealed interface FieldError {
        data object IllegalLength : FieldError
        data object IllegalInput : FieldError
    }
}