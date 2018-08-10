package com.curious.programmers.carservice

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias

@TypeAlias("car")
class CarAggregate(@Id val registration: String,
                   val make: String,
                   val model: String)