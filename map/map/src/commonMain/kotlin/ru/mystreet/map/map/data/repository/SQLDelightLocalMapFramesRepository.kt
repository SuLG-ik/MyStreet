package ru.mystreet.map.map.data.repository

import ru.mystreet.core.db.MapFrameQueries
import ru.mystreet.core.time.DateTimeService
import ru.mystreet.map.map.data.converter.SQLDelightFramedMapObjectConverter
import ru.mystreet.map.map.domain.entity.MapFrame
import ru.mystreet.map.map.domain.entity.SavedMapFrame
import ru.mystreet.map.map.domain.repository.LocalMapFramesRepository

class SQLDelightLocalMapFramesRepository(
    private val mapFrameQueries: MapFrameQueries,
    private val dateTimeService: DateTimeService,
    private val sqlDelightFramedMapObjectConverter: SQLDelightFramedMapObjectConverter,
) : LocalMapFramesRepository {

    override suspend fun getLoadedMapFrame(frame: MapFrame): SavedMapFrame? {
        return mapFrameQueries.transactionWithResult {
            with(sqlDelightFramedMapObjectConverter) {
                mapFrameQueries.getMapFrame(frame.column.toLong(), frame.row.toLong())
                    .executeAsOneOrNull()?.convert()
            }
        }
    }

    override suspend fun saveLoadedMapFrame(frame: MapFrame) {
        mapFrameQueries.transaction {
            mapFrameQueries.upserMapFrame(
                updatedAt = dateTimeService.currentDateTime(),
                column = frame.column.toLong(),
                row = frame.row.toLong(),
            )
        }
    }
}