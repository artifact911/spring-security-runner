package users

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiUsersApplication

fun main(args: Array<String>) {
    runApplication<ApiUsersApplication>(*args)
}