package ru.mystreet.map.domain.validator

import ru.mystreet.map.domain.entity.TitleField

interface MapObjectFieldValidator {

    fun validateTitle(value: String): TitleField

}