package com.curious.programmers.carservice.configuration.security

import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import reactor.core.publisher.Mono


class AuthenticationManager(val jwtUtil: JWTUtils) : ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        val authToken = authentication.credentials.toString()

        var username: String?
        try {
            username = jwtUtil.getUsernameFromToken(authToken)
        } catch (e: Exception) {
            username =
                    null
        }
        val validateToken = jwtUtil.validateToken(authToken)

        if (username != null && validateToken) {
            val allClaimsFromToken = jwtUtil.getAllClaimsFromToken(authToken)
            val rolesMap = allClaimsFromToken.get("role", List::class.java)

            val roles = rolesMap.map { SimpleGrantedAuthority(it as String?) }
            val auth = UsernamePasswordAuthenticationToken(
                    username, null,
                    roles
            )
            return Mono.just(auth)
        } else {
            return Mono.empty()
        }
    }
}