package ru.mystreet.account.component.profile

import com.arkivanov.decompose.value.Value
import ru.mystreet.account.domain.entity.AccountProfileFull
import ru.mystreet.core.component.ValueContainer

interface AccountProfileHost {

    val isLoading: Value<Boolean>

    val account: Value<ValueContainer<AccountProfileFull?>>

}