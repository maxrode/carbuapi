package org.maxogen.carburapi.service

import org.maxogen.carburapi.model.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class CarburService {

    @Value("\${api.url}")
    private lateinit var url: String

    fun search(area: Area, filter: ((Station) -> Boolean)? = null): Map<Int, Station?>? {
        var result: List<Station>? = client.postForEntity(url, area, SearchResponse::class.java).body?.results?.toList()
        if (filter != null) result = result?.filter(filter)
        return result?.let { choseBestByFuelId(it) }
    }

    private fun choseBestByFuelId(
        data: List<Station>
    ): Map<Int, Station?> {
        val fuels = data.flatMap { station -> station.fuels.map { fuel -> fuel.fuelId } }
        return fuels.associateWith { fuel ->
            var bestStation: Station? = null
            var minPrice: Float? = null

            data.forEach { station ->
                val tmpMin = station.fuels.filter { it.fuelId == fuel }.minByOrNull { it.price }?.price

                if (minPrice != null && tmpMin != null) {
                    if (tmpMin < minPrice!!) {
                        minPrice = tmpMin
                        bestStation = station
                    }
                } else if (minPrice == null) {
                    bestStation = station
                    minPrice = tmpMin
                }
            }
            bestStation
        }
    }

    companion object {
        private val client = RestTemplate()
    }
}