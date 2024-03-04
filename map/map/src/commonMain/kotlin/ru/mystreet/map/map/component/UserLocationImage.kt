package ru.mystreet.map.map.component

import ru.mystreet.map.Placemark
import ru.mystreet.map.RGBA
import ru.mystreet.map.UserLocationObjectListener
import ru.mystreet.map.UserLocationView
import ru.mystreet.map.image.ImageProvider

class UserLocationImage(
    private val image: ImageProvider,
) : UserLocationObjectListener() {

    val iconPoint get() = placemark?.geomety

    var placemark: Placemark? = null

    override fun onObjectAdded(p0: UserLocationView) {
        placemark = p0.pin
        p0.arrow.setIcon(image)
        p0.pin.setIcon(image)
        p0.circle.fillColor = RGBA(108, 176, 244, 50)
    }

    override fun onObjectRemoved(p0: UserLocationView) {

    }

    override fun onObjectUpdated(p0: UserLocationView) {
        p0.arrow.setIcon(image)
        p0.pin.setIcon(image)
        p0.circle.fillColor = RGBA(108, 176, 244, 50)
    }
}