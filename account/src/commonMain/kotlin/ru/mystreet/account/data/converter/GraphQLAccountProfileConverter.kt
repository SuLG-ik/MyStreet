package ru.mystreet.account.data.converter

import ru.mystreet.account.domain.entity.AccountProfileFull
import ru.mystreet.map.account.fragment.AccountProfileFull as GraphQLAccountProfileFull

class GraphQLAccountProfileConverter {

    fun GraphQLAccountProfileFull.convert(): AccountProfileFull {
        return AccountProfileFull(
            username = username,
            email = email,
            name = name
        )
    }

}