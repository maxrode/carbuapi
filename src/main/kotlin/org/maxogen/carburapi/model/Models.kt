package org.maxogen.carburapi.model

data class Area(var points: MutableCollection<Point>, var radius: Int)

data class Point(var lat: Double, var lng: Double)

data class Score(var fuelName: String, var bestOption: Station)