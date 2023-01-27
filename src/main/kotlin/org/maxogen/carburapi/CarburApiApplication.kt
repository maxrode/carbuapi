package org.maxogen.carburapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CarburApiApplication

fun main(args: Array<String>) {
    runApplication<CarburApiApplication>(*args)
}
