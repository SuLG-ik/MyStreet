package ru.mystreet.map.map.data.repository

import ru.mystreet.core.db.MapFrameQueries
import ru.mystreet.core.db.MapObjectPartQueries
import ru.mystreet.core.time.DateTimeService
import ru.mystreet.map.map.data.converter.SQLDelightFramedMapObjectConverter
import ru.mystreet.map.map.domain.entity.FramedMapObjects
import ru.mystreet.map.map.domain.entity.MapFrame
import ru.mystreet.map.map.domain.repository.LocalFramedMapObjectsRepository
import kotlin.time.ExperimentalTime

class SQLDelightLocalFramedMapObjectsRepository(
    private val mapObjectPartQueries: MapObjectPartQueries,
    private val mapFrameQueries: MapFrameQueries,
    private val dateTimeService: DateTimeService,
    private val converter: SQLDelightFramedMapObjectConverter,
) : LocalFramedMapObjectsRepository {

    override suspend fun saveMapObjects(objects: FramedMapObjects) {
        mapObjectPartQueries.transaction {
            val frameId = mapFrameQueries.upserMapFrame(
                updatedAt = dateTimeService.currentDateTime(),
                column = objects.frame.column.toLong(),
                row = objects.frame.row.toLong(),
            ).executeAsOne()
            transaction {
                mapObjectPartQueries.deleteAllByFrame(frameId)
                objects.objects.forEach { mapObject ->
                    mapObjectPartQueries.insertMapObjectParts(
                        title = mapObject.title,
                        category = mapObject.category.id,
                        latitude = mapObject.point.latitude.value,
                        longitude = mapObject.point.longitude.value,
                        frameId = frameId,
                        id = mapObject.id,
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalTime::class)
    override suspend fun getMapObjects(frame: MapFrame): FramedMapObjects? {
        return mapObjectPartQueries.transactionWithResult {
            val savedFrame = mapFrameQueries.getMapFrame(frame.column.toLong(), frame.row.toLong())
                .executeAsOneOrNull() ?: return@transactionWithResult null
            val objects =
                mapObjectPartQueries.getMapObjectPartsByFrame(savedFrame.id).executeAsList()
            FramedMapObjects(
                frame = frame,
                objects = with(converter) { objects.map { it.convert() } },
            )
        }
    }
}