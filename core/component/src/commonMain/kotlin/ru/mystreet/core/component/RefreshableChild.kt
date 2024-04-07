package ru.mystreet.core.component

import com.arkivanov.decompose.Child
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.active
import com.arkivanov.decompose.value.Value

fun interface RefreshableComponent {
    fun onRefresh()
}

interface RefreshableChild {

    val component: RefreshableComponent

    fun onRefresh() {
        component.onRefresh()
    }

    companion object {
        private fun refreshInstance(child: Any) {
            if (child is RefreshableChild) {
                child.onRefresh()
            }
        }

        private fun refreshChild(child: Child.Created<*, *>) {
            refreshInstance(child.instance)
        }

        fun Value<ChildStack<*, *>>.refreshActive() {
            refreshChild(active)
        }
    }
}