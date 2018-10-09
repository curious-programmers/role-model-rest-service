package com.curious.programmers.carservice.configuration.security

import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository
import reactor.core.publisher.Mono


@Configuration
@EnableWebFluxSecurity
class SecurityConfiguration {


    @Value("\${jwt.secret}")
    var secret: String? = null

    @Value("\${jwt.expiration}")
    var expiration: Long? = null


    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity, authenticationWebFilter: JwtAuthenticationWebFilter,
                                  unauthorizedEntryPoint: UnauhorizedEntryPoint,
                                  reactiveAuthenticationManager: ReactiveAuthenticationManager): SecurityWebFilterChain {
        http
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedEntryPoint)
                .and()
                .authenticationManager(reactiveAuthenticationManager)
                .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .authorizeExchange()
                .pathMatchers("/resources/**",
                        "/auth/**",
                        "/info/**",
                        "/favicon.ico")
                .permitAll()
                .anyExchange().authenticated()
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .csrf().disable()
                .logout().disable()


        return http.build()
    }

    @Bean
    fun reactiveAuthenticationManager(): ReactiveAuthenticationManager {
        return ReactiveAuthenticationManager { authentication ->
            Mono.just(authentication)
        }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    @Bean
    fun jwtUtils(): JWTUtils = JWTUtils(Keys.hmacShaKeyFor(secret?.toByteArray(Charsets.UTF_8)))

    @Bean
    fun securityContextRepository(): WebSessionServerSecurityContextRepository {
        return WebSessionServerSecurityContextRepository()
    }

    @Bean
    fun unauthorizedEntryPoint(): UnauhorizedEntryPoint = UnauhorizedEntryPoint()

}