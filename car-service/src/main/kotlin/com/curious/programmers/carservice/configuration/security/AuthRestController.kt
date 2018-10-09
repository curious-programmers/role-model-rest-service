package com.curious.programmers.carservice.configuration.security

import com.curious.programmers.carservice.userservice.UserRepository
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.notFound
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
class AuthRestController(val userRepository: UserRepository, val jwtUtils: JWTUtils) {

    @PostMapping("/auth/token")
    @CrossOrigin("*")
    fun getToken(@RequestBody authRequest: AuthRequest): Mono<ResponseEntity<AuthResponse>> {
        return userRepository.findByName(authRequest.username)
                .map { it ->
                    ok().contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8)
                            .body(AuthResponse(jwtUtils.generateToken(it)))
                }
                .defaultIfEmpty(notFound().build())

    }


}