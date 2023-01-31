package org.maxogen.carburapi.model

import kotlin.math.max
import kotlin.math.min

class Box(a: Point, b: Point) {

    val upperLeft = Point(lat = min(a.lat, b.lat), lng = min(a.lng, b.lng))
    val upperRight = Point(lat = min(a.lat, b.lat), lng = max(a.lng, b.lng))
    val lowerLeft = Point(lat = max(a.lat, b.lat), lng = min(a.lng, b.lng))
    val lowerRight = Point(lat = min(a.lat, b.lat), lng = max(a.lng, b.lng))

    val edges = listOf(upperLeft, upperRight, lowerLeft, lowerRight)
}