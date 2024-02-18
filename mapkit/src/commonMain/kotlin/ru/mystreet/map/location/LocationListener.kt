package ru.mystreet.map.location

data class LocationListenerConfig(
    val desiredAccuracy: Double = 0.0,
    val minTime: Long = 100,
    val minDistance: Double = 0.0,
    val background: Boolean = false,
    val filteringMode: FilteringMode = FilteringMode.ON,
)

enum class FilteringMode {
    OFF, ON,
}

fun interface LocationListener {

    fun onUpdate(location: Location)

    fun onStatusUpdate(status: LocationStatus) {}

}