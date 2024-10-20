package ru.mystreet.map.map.component

import androidx.compose.ui.graphics.Color
import ru.sulgik.mapkit.compose.utils.toMapkitColor
import ru.sulgik.mapkit.layers.ObjectEvent
import ru.sulgik.mapkit.map.ImageProvider
import ru.sulgik.mapkit.map.PlacemarkMapObject
import ru.sulgik.mapkit.user_location.UserLocationObjectListener
import ru.sulgik.mapkit.user_location.UserLocationView

class UserLocationImage(
    private val image: ImageProvider,
) : UserLocationObjectListener() {

    val iconPoint get() = placemark?.geometry

    var placemark: PlacemarkMapObject? = null

    override fun onObjectUpdated(view: UserLocationView, event: ObjectEvent) {
        view.arrow.setIcon(image)
        view.pin.setIcon(image)
        view.accuracyCircle.fillColor = Color(108, 176, 244, 50).toMapkitColor()
    }

    override fun onObjectAdded(view: UserLocationView) {
        placemark = view.pin
        view.arrow.setIcon(image)
        view.pin.setIcon(image)
        view.accuracyCircle.fillColor = Color(108, 176, 244, 50).toMapkitColor()
    }

    override fun onObjectRemoved(view: UserLocationView) {
    }
}