package com.curious.programmers.carservice

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.repository.CrudRepository

interface CarRepository : ReactiveMongoRepository<CarAggregate, String>