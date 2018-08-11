package com.curious.programmers.carservice

import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.support.beans
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono

@SpringBootApplication
class CarServiceApplication

fun main(args: Array<String>) {

    SpringApplicationBuilder()
            .sources(CarServiceApplication::class.java)
            .initializers(beans {
                bean {
                    ApplicationRunner {
                        println("hello ")
                    }
                }
                bean {
                    router {
                        ("/info" and accept(MediaType.TEXT_HTML)).nest {
                            GET("/hello") { ServerResponse.ok().body(fromObject(Mono.just("hello"))) }
                        }
                    }
                }
            })
            .run(*args)
}
