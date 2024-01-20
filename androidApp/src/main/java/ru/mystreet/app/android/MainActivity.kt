package ru.mystreet.app.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.decompose.retainedComponent
import ru.mystreet.app.appComponent
import ru.mystreet.app.context.RootComponent
import ru.mystreet.app.context.withDI

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalDecomposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val componentContext: RootComponent = retainedComponent { appComponent(it.withDI()) }
        setContent {
            componentContext.RootContent()
        }
    }
}