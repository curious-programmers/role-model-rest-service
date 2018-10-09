package com.curious.programmers.carservice.configuration.security

import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class JwtAuthenticationWebFilter(authenticationManager: ReactiveAuthenticationManager?) : AuthenticationWebFilter(authenticationManager) {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {

        println("filter")
        return super.filter(exchange, chain)
    }
}