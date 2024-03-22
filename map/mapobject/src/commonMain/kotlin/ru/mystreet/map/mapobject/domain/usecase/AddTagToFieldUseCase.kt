package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.TagsField

class AddTagToFieldUseCase {

    operator fun invoke(field: TagsField): TagsField {
        if (field.tags.tags.size >= field.tags.maxTags)
            return field
        val newTagValue = field.value.trim()
        if (newTagValue.isEmpty())
            return field.copy(value = newTagValue)
        if (field.value in field.tags.tags)
            return field.copy(value = "")
        val newCurrentTags = field.tags.tags.add(newTagValue)
        return field.copy(
            tags = field.tags.copy(tags = newCurrentTags),
            isInputAvailable = newCurrentTags.size < MAX_TAGS,
            value = "",
        )
    }

    companion object {
        const val MAX_TAGS = 5
    }

}