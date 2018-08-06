package com.curious.programmers.carservice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux


data class Car(val id: String, val make: String, val model: String)


@RestController
class CarController {

    @Autowired
    lateinit var carRepository: CarRepository


    @PostMapping("cars")

    fun post(@RequestBody car: Car): Flux<CarAggregate> {

        var subscribe = carRepository.save(CarAggregate(car.id, car.make, car.model)).subscribe()

        return carRepository.findAll()
    }


}