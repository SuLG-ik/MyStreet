package ru.mystreet.map.domain.usecase

import ru.mystreet.map.domain.entity.TagsField

class RemoveTagFromFieldUseCase {

    operator fun invoke(tag: String, field: TagsField): TagsField {
        val newCurrentTags = field.tags.tags.removeAll { it == tag }
        return field.copy(
            tags = field.tags.copy(tags = newCurrentTags),
            isInputAvailable = newCurrentTags.size < MAX_TAGS,
        )
    }

    companion object {
        const val MAX_TAGS = 5
    }

}