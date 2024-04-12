package ru.mystreet.account.data.converter

import org.koin.core.annotation.Factory
import ru.mystreet.account.domain.entity.AccountProfileFull
import ru.mystreet.map.account.fragment.AccountProfileFull as GraphQLAccountProfileFull

@Factory
class GraphQLAccountProfileConverter {

    fun GraphQLAccountProfileFull.convert(): AccountProfileFull {
        return AccountProfileFull(
            username = username,
            email = email,
            name = name
        )
    }

}