package ru.mystreet.errors.data.resolver

import ru.mystreet.errors.domain.ErrorConfigResolver
import ru.mystreet.errors.domain.ErrorInfo
import ru.mystreet.errors.domain.UniqueIdGenerator
import ru.mystreet.errors.domain.exception.HandleableError

class HandleableErrorResolver(
    private val idGenerator: UniqueIdGenerator,
) : ErrorConfigResolver<HandleableError> {

    override fun resolve(value: HandleableError): ErrorInfo {
        return ErrorInfo(
            uniqueId = idGenerator.next(),
            data = value.data,
            config = value.config,
            parent = value,
        )
    }

}