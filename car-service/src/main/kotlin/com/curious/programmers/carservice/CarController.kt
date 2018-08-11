package com.curious.programmers.carservice

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


data class AddCarRequest(val id: String, val make: String, val model: String)


@RestController
class CarController(val carService: CarService) {

    @PostMapping("cars")
    fun post(@RequestBody car: AddCarRequest): Mono<CarAggregate> =
            carService.insert(car)

    @GetMapping("cars")
    fun get(): Flux<CarAggregate> = carService.all()
}