package com.curious.programmers.carservice

import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface CarRepository : ReactiveMongoRepository<CarAggregate, String>