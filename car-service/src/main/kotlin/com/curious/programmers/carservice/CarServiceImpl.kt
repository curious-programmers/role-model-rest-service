package com.curious.programmers.carservice

import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class CarServiceImpl(val repository: CarRepository) : CarService {

    override fun insert(carDto: AddCarRequest): Mono<CarAggregate> {
        return repository.save(CarAggregate(carDto.id, carDto.make, carDto.model))
    }

    override fun all(): Flux<CarAggregate> {
        return repository.findAll()
    }

}