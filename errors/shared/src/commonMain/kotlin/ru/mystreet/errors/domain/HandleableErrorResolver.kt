package ru.mystreet.errors.domain

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