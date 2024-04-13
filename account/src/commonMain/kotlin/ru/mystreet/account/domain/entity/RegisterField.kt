package ru.mystreet.account.domain.entity

import ru.mystreet.uikit.ValidatedField

data class RegisterField(
    val name: ValidatedField<FieldError>,
    val login: ValidatedField<FieldError>,
    val email: ValidatedField<FieldError>,
    val password: ValidatedField<FieldError>,
    val passwordRepeat: ValidatedField<FieldError>,
)