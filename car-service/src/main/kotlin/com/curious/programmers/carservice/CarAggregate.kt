package com.curious.programmers.carservice

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "cars")
data class CarAggregate(@Id val registration: String,
                        val make: String,
                        val model: String)