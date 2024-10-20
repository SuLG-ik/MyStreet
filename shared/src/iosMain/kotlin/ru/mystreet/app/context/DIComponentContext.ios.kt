package ru.mystreet.app.context

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.ApplicationLifecycle
import ru.mystreet.core.component.withDI

fun DefaultDIComponentContext() = DefaultComponentContext(ApplicationLifecycle()).withDI()