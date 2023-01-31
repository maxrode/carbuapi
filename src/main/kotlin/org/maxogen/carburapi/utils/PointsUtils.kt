package org.maxogen.carburapi.utils

import org.maxogen.carburapi.model.Area
import org.maxogen.carburapi.model.Box
import org.maxogen.carburapi.model.Point
import kotlin.math.*

object PointsUtils {

    private const val haversineConst = 6371100

    fun boxSearch(fromPoint: Point, toPoint: Point, radius: Int = 10) {
        val box = Box(fromPoint, toPoint)
        val xDist = haversineDistance(box.upperLeft, box.upperRight)
        val yDist = haversineDistance(box.upperLeft, box.lowerLeft)

        val numXScan = (xDist / radius * 1000).roundToInt()
        val numYScan = (yDist / radius * 1000).roundToInt()

        val dist = min(xDist / numXScan, yDist / numYScan)

        val matrix = produceMatrixScan(box.upperLeft, dist, numXScan, numYScan, radius)


    }

    private fun produceMatrixScan(start: Point, dist: Double, numX: Int, numY: Int, radius: Int): List<Area> {
        val matrix = mutableListOf<Area>()
        IntRange(1, numX).forEach { nx ->
            IntRange(1, numY).forEach { ny ->
                val point = Point(lat = start.lat + (dist * nx), lng = start.lng + (dist * ny))
                matrix.add(Area(points = mutableListOf(point), radius = radius))
            }
        }
        return matrix
    }

    private fun haversineDistance(from: Point, to: Point): Double {
        val lat1 = Math.toRadians(from.lat)
        val lon1 = Math.toRadians(from.lng)
        val lat2 = Math.toRadians(to.lat)
        val lon2 = Math.toRadians(to.lng)
        return haversineConst * acos(sin(lat1) * sin(lat2) + cos(lat1) * cos(lat2) * cos(lon1 - lon2))
    }

}