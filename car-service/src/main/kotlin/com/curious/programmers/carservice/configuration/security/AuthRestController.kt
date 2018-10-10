package com.curious.programmers.carservice.configuration.security

import com.curious.programmers.carservice.userservice.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.notFound
import org.springframework.http.ResponseEntity.ok
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
class AuthRestController(val userRepository: UserRepository, val jwtUtils: JWTUtils,
                         val passwordEncoder: PasswordEncoder) {

    @PostMapping("/auth/token")
    @CrossOrigin("*")
    fun getToken(@RequestBody authRequest: AuthRequest): Mono<ResponseEntity<AuthResponse>> {
        return userRepository.findByName(authRequest.username)
                .map { it ->
                    if (passwordEncoder.matches(authRequest.password, it.passwordCharacters.toString())) {
                        ok().contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8)
                                .body(AuthResponse(jwtUtils.generateToken(it)))
                    } else {
                        ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
                    }

                }
                .defaultIfEmpty(notFound().build())

    }


}