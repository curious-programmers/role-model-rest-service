package com.curious.programmers.carservice.configuration.security

import com.curious.programmers.carservice.userservice.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class JWTUtilsTest {

    val key = Keys.secretKeyFor(SignatureAlgorithm.HS512)
    val jwtUtils = JWTUtils(key)

    @Test
    fun generateToken() {
        val user = User("dawid", "balbalbal", mutableListOf("ADMIN"))

        val generateToken1 = jwtUtils.generateToken(user)

        println(generateToken1)

    }
}