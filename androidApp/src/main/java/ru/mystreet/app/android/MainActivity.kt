package ru.mystreet.app.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.retainedComponent
import ru.mystreet.app.appComponent
import ru.mystreet.core.component.withDI
import ru.mystreet.root.component.AppRoot
import ru.mystreet.root.ui.AppRootUI

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalDecomposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val appRoot: AppRoot = retainedComponent { appComponent(it.withDI()) }
        setContent {
            AppRootUI(component = appRoot)
        }
    }
}