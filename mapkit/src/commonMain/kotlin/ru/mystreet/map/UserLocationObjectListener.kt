package ru.mystreet.map

expect abstract class UserLocationObjectListener() {

    abstract fun onObjectAdded(p0: UserLocationView)

    abstract fun onObjectRemoved(p0: UserLocationView)

    abstract fun onObjectUpdated(p0: UserLocationView)

}