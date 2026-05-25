package auth.controller

import auth.dto.LoginRequest
import auth.dto.TokenResponse
import auth.dto.ValidateRequest
import auth.dto.ValidateResponse
import auth.service.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/login")
    suspend fun login(@RequestBody request: LoginRequest): TokenResponse =
        authService.login(request)

    @PostMapping("/validate")
    suspend fun validate(@RequestBody request: ValidateRequest): ValidateResponse =
        authService.validate(request.token)
}