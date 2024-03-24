package ru.mystreet.map.component.editmap

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.mystreet.map.component.add.EditMapNewObjectInfo
import ru.mystreet.map.component.add.EditMapNewObjectLoading
import ru.mystreet.map.component.add.EditMapSelectCategory
import ru.mystreet.map.domain.entity.MapObjectCategory

interface EditMapBottomBar {

    val childStack: Value<ChildStack<*, Child>>

    val isVisible: Value<Boolean>

    fun setCategories(categories: List<MapObjectCategory>)

    sealed interface Child {

        data class SelectCategory(val component: EditMapSelectCategory) : Child

        data class EditInfo(val component: EditMapNewObjectInfo) : Child

        data class Loading(val component: EditMapNewObjectLoading) : Child

    }

}