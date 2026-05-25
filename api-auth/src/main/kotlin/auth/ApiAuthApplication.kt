package auth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class ApiAuthApplication

fun main(args: Array<String>) {
    runApplication<ApiAuthApplication>(*args)
}