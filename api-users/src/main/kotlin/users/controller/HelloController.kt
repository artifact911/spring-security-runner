package users.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import users.dto.HelloRequest
import users.dto.HelloResponse

@RestController
@RequestMapping("/users")
class HelloController {

    @PostMapping("/hello")
    suspend fun hello(@RequestBody request: HelloRequest): HelloResponse {
        return HelloResponse("Hello ${request.name}!")
    }
}