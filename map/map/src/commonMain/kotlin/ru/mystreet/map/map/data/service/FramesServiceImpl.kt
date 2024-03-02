package ru.mystreet.map.map.data.service

import ru.mystreet.map.geomety.Point
import ru.mystreet.map.geomety.VisibleArea
import ru.mystreet.map.map.domain.entity.MapFrame
import ru.mystreet.map.map.domain.service.FramesService

class FramesServiceImpl : FramesService {

    override fun calculateFrames(visibleArea: VisibleArea): List<MapFrame> {
        val topLeft = Point(
            minOf(visibleArea.topLeft.latitude, visibleArea.topRight.latitude),
            maxOf(visibleArea.topLeft.longitude, visibleArea.topRight.longitude)
        )
        val bottomRight = Point(
            minOf(visibleArea.topLeft.latitude, visibleArea.topRight.latitude),
            maxOf(visibleArea.topLeft.longitude, visibleArea.topRight.longitude)
        )
        val firstFrameColumn = ((topLeft.latitude.value + LATITUDE_OFFSET) / FRAME_SIZE).toInt()
        val lastFrameColumn = ((bottomRight.latitude.value + LATITUDE_OFFSET) / FRAME_SIZE).toInt()
        val firstFrameRow = ((topLeft.longitude.value + LONGITUDE_OFFSET) / FRAME_SIZE).toInt()
        val lastFrameRow = ((bottomRight.longitude.value + LONGITUDE_OFFSET) / FRAME_SIZE).toInt()
        val requestColumns =
            (firstFrameColumn - lastFrameColumn + 1) * (firstFrameRow - lastFrameRow + 1)
        if (requestColumns > MAX_FRAMES_SIZE)
            return emptyList()
        val requestFrames = mutableListOf<MapFrame>()
        for (column in firstFrameColumn..lastFrameColumn) {
            for (row in firstFrameRow..lastFrameRow) {
                requestFrames.add(MapFrame(column - COLUMN_OFFSET, row - ROW_OFFSET))
            }
        }
        return requestFrames.toList()
    }


    companion object {
        const val FRAME_SIZE = 0.02
        const val MAX_FRAMES_SIZE = 4

        const val COLUMN_OFFSET = 9000
        const val ROW_OFFSET = 4500

        const val LATITUDE_OFFSET = 180f
        const val LONGITUDE_OFFSET = 90f

    }

}