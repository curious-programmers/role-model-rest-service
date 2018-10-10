package com.curious.programmers.carservice.configuration.security

import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono


class SecurityContextRepository(val authenticationManager: ReactiveAuthenticationManager) : ServerSecurityContextRepository {
    override fun save(swe: ServerWebExchange, sc: SecurityContext): Mono<Void> {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun load(swe: ServerWebExchange): Mono<SecurityContext> {
        val request = swe.request
        val authHeader = request.headers.getFirst("Authorization")

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            val authToken = authHeader.substring(7)
            val auth = UsernamePasswordAuthenticationToken(authToken, authToken)
            return this.authenticationManager.authenticate(auth).map { authentication -> SecurityContextImpl(authentication) }
        } else {
            return Mono.empty()
        }
    }
}