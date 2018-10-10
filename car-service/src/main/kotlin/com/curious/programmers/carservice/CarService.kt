package com.curious.programmers.carservice

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


interface CarService {

    fun all(): Flux<CarAggregate>

    fun insert(carDto: AddCarRequest): Mono<CarAggregate>
}