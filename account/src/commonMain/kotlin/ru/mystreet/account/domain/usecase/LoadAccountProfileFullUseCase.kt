package ru.mystreet.account.domain.usecase

import ru.mystreet.account.domain.entity.AccountProfileFull
import ru.mystreet.account.domain.repository.AccountProfileRepository

class LoadAccountProfileFullUseCase(
    private val accountProfileRepository: AccountProfileRepository,
) {

    suspend operator fun invoke(): AccountProfileFull {
        return accountProfileRepository.info()
    }

}