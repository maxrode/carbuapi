package org.maxogen.carburapi.service

import org.maxogen.carburapi.model.Area
import org.maxogen.carburapi.model.Point
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime

@Component
class Test : CommandLineRunner {

    @Autowired
    lateinit var carburService: CarburService

    override fun run(vararg args: String?) {
        val area = Area(points = mutableListOf(Point(lat = 46.067207, lng = 11.133737)), radius = 10)

        carburService.search(area) { station ->
            station.brand.contains("eni", true)
                    && station.insertDate.isAfter(
                ZonedDateTime.of(
                    LocalDate.now().atStartOfDay(), ZoneId.systemDefault()
                )
            )
        }?.forEach { (k, v) ->
            logger.info("key = $k, value=$v")
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(Test::class.java)!!
    }
}