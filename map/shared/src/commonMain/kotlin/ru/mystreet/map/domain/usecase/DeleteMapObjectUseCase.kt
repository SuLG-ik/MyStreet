package ru.mystreet.map.domain.usecase

class DeleteMapObjectUseCase(
    private val deleteRemoteMapObjectUseCase: DeleteRemoteMapObjectUseCase,
    private val deleteLocalMapObjectUseCase: DeleteLocalMapObjectUseCase,
) {
    suspend operator fun invoke(id: Long) {
        deleteRemoteMapObjectUseCase(id)
        deleteLocalMapObjectUseCase(id)
    }
}

