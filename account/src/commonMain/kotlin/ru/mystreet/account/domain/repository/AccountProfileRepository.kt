package ru.mystreet.account.domain.repository

import ru.mystreet.account.domain.entity.AccountProfileFull

interface AccountProfileRepository {

    suspend fun info(): AccountProfileFull

}