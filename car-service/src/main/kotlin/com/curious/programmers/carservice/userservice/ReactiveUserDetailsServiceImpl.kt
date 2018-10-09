package com.curious.programmers.carservice.userservice

import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class ReactiveUserDetailsServiceImpl(val userRepository: UserRepository) : ReactiveUserDetailsService {
    override fun findByUsername(username: String?): Mono<UserDetails> {
        return if (username == null) Mono.empty() else {
            userRepository.findByName(username).map { SecurityUserDetails(it) }
        }
    }
}