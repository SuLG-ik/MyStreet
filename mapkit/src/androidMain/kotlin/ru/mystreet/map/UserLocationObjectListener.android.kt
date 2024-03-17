package ru.mystreet.map

import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.map.CircleMapObject
import com.yandex.mapkit.user_location.UserLocationObjectListener

actual abstract class UserLocationObjectListener : UserLocationObjectListener {


    override fun onObjectAdded(p0: com.yandex.mapkit.user_location.UserLocationView) {
        onObjectAdded(p0.toCommon())
    }

    override fun onObjectRemoved(p0: com.yandex.mapkit.user_location.UserLocationView) {
        onObjectRemoved(p0.toCommon())
    }

    override fun onObjectUpdated(
        p0: com.yandex.mapkit.user_location.UserLocationView,
        p1: ObjectEvent,
    ) {
        onObjectUpdated(p0.toCommon())
    }

    actual abstract fun onObjectAdded(p0: UserLocationView)
    actual abstract fun onObjectRemoved(p0: UserLocationView)
    actual abstract fun onObjectUpdated(p0: UserLocationView)


}

private fun com.yandex.mapkit.user_location.UserLocationView.toCommon(): UserLocationView {
    return UserLocationView(
        pin = pin.toCommon(),
        arrow = arrow.toCommon(),
        circle = accuracyCircle.toCommon()
    )
}

fun CircleMapObject.toCommon(): ru.mystreet.map.CircleMapObject {
    return CircleMapObject(
        nativeCircleMapObject = this,
    )
}
