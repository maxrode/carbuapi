package org.maxogen.carburapi.service

import org.maxogen.carburapi.model.Box
import org.maxogen.carburapi.model.Point
import org.maxogen.carburapi.utils.PointsUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class Test : CommandLineRunner {

    @Autowired
    lateinit var carburService: CarburService

    override fun run(vararg args: String?) {
        val pointFrom = Point(lat = 46.009940, lng = 11.304565)
        val pointTo = Point(lat = 46.050461, lng = 11.411289)


        val box = Box(pointFrom, pointTo)

        println(box.edges)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(Test::class.java)!!
    }
}