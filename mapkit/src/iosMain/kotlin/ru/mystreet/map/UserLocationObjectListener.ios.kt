package ru.mystreet.map

actual abstract class UserLocationObjectListener {
    actual abstract fun onObjectAdded(p0: UserLocationView)
    actual abstract fun onObjectRemoved(p0: UserLocationView)
    actual abstract fun onObjectUpdated(p0: UserLocationView)


}