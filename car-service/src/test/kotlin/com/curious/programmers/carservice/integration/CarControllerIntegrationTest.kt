package com.curious.programmers.carservice.integration

import com.curious.programmers.carservice.AddCarRequest
import com.curious.programmers.carservice.CarAggregate
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono


@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureWebTestClient
class CarControllerIntegrationTest {

    @Autowired
    private lateinit var client: WebTestClient

    @Test
    fun `Add car to repository`() {

        val addCarRequest = AddCarRequest("KT3R987", "BMW", "325i")

        client.post()
                .uri("/cars")
                .body(Mono.just(addCarRequest), AddCarRequest::class.java)
                .exchange()
                .expectStatus()
                .isOk
                .returnResult(CarAggregate::class.java)

    }
}