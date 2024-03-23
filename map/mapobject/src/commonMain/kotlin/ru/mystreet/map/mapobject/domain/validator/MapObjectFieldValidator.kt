package ru.mystreet.map.domain.validator

import ru.mystreet.map.domain.entity.FieldError

interface MapObjectFieldValidator {

    fun validateTitle(value: String): FieldError?

    fun validateDescription(value: String): FieldError?

}