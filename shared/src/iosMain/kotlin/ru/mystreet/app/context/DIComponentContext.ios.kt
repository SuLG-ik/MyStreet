package ru.mystreet.app.context

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.lifecycle.ApplicationLifecycle

@OptIn(ExperimentalDecomposeApi::class)
fun DefaultDIComponentContext() = DefaultComponentContext(ApplicationLifecycle()).withDI() 