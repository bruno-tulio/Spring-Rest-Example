package com.homeio.forum

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class ForumApplication

fun main(args: Array<String>) {
    runApplication<ForumApplication>(*args)
}

