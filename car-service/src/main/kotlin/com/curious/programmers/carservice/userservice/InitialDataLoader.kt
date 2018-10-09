package com.curious.programmers.carservice.userservice

import com.curious.programmers.carservice.CarAggregate
import com.curious.programmers.carservice.CarRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.stereotype.Component

@Component
class InitialDataLoader @Autowired
constructor(private val userRepo: UserRepository, private val carRepository: CarRepository) : CommandLineRunner {

    override fun run(vararg args: String) {
        var encode = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("password")

        var deleteAll = userRepo.deleteAll().subscribe()
        var save = userRepo.save(User("Dawid", encode))
        save.subscribe()
        println(userRepo.findByName("Dawid").block())

        carRepository.save(CarAggregate("KR7CR46", "BMW", "118i")).subscribe()
    }
}