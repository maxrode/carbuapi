package org.maxogen.carburapi.model

import java.time.ZonedDateTime

data class Fuel(var id: Int, var price: Float, var name: String, var fuelId: Int, var isSelf: Boolean)

data class Station(
    var id: Int,
    var name: String,
    var fuels: MutableCollection<Fuel>,
    var location: Point,
    var insertDate: ZonedDateTime,
    var address: String,
    var brand: String
)

data class SearchResponse(var success: Boolean, var center: Point, var results: MutableCollection<Station>)