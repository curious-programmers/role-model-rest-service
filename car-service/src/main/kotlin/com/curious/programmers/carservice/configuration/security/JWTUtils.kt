package com.curious.programmers.carservice.configuration.security

import com.curious.programmers.carservice.userservice.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import java.security.Key
import java.util.*


class JWTUtils(val key: Key) {


    fun generateToken(subject: String): String {

        val claims = mutableMapOf<String, Any>(Pair("potato", "patator"))

        val expirationDate = Date(Date().time + 1000 * 60 * 24 * 365)


        return Jwts.builder()
                .setIssuedAt(Date())
                .setSubject(subject)
                .setExpiration(expirationDate)
//                .setClaims(claims)
                .signWith(key)
                .compact()
    }

    fun decodeToken(token: String): String {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().audience
    }


    fun getAllClaimsFromToken(token: String): Claims {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody()
    }

    fun getUsernameFromToken(token: String): String {
        return getAllClaimsFromToken(token).subject
    }

    fun getExpirationDateFromToken(token: String): Date {
        return getAllClaimsFromToken(token).expiration
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = getExpirationDateFromToken(token)
        return expiration.before(Date())
    }

    fun generateToken(user: User): String {
        val claims = mutableMapOf<String, List<String>>(Pair("role", user.roles))
        return doGenerateToken(claims, user.name)
    }

    private fun doGenerateToken(claims: Map<String, Any>, username: String): String {
        val expirationTimeLong = java.lang.Long.parseLong("28800") //in second

        val createdDate = Date()
        val expirationDate = Date(createdDate.time + expirationTimeLong * 1000)
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact()
    }

    fun validateToken(token: String): Boolean {
        return (!isTokenExpired(token))
    }

}