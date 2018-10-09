package com.curious.programmers.carservice.configuration.security

import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono


@Component
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

//            val claims = jwtUtil!!.getAllClaimsFromToken(authToken)
//            val rolesMap = claims.get("role", List<*>::class.java)
//            val roles = mutableListOf<String>()
//            for (rolemap in rolesMap) {
//                roles.add(rolemap)
//            }

            val allClaimsFromToken = jwtUtil.getAllClaimsFromToken(authToken)
            val auth = UsernamePasswordAuthenticationToken(
                    username, null,
                    listOf(SimpleGrantedAuthority("ADMIN"))
            )
            return Mono.just(auth)
        } else {
            return Mono.empty()
        }
    }
}