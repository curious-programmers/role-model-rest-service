package com.curious.programmers.carservice.userservice

import org.springframework.data.mongodb.core.mapping.Document


@Document
data class User(val name: String,
                val passwordCharacters: CharSequence,
                val roles: MutableList<String> = mutableListOf("USER")) {

    override fun toString(): String {
        return "User(name='$name', passwordCharacters=$passwordCharacters)"
    }
}

