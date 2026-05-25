package auth.service

import auth.dto.LoginRequest
import auth.dto.TokenResponse
import auth.dto.ValidateResponse
import auth.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService
) {

    suspend fun login(
        request: LoginRequest
    ): TokenResponse {

        val user = userRepository.findByLogin(request.login)
            ?: throw RuntimeException("Invalid credentials")

        val validPassword = passwordEncoder.matches(
            request.password,
            user.passwordHash
        )

        if (!validPassword) {
            throw RuntimeException("Invalid credentials")
        }

        val token = jwtService.generateToken(
            userId = user.id,
            roles = user.roles
        )

        return TokenResponse(
            accessToken = token,
            expiresIn = 3600
        )
    }

    suspend fun validate(
        token: String
    ): ValidateResponse {

        return try {

            val jwt = jwtService.validate(token)

            ValidateResponse(
                valid = true,
                userId = jwt.subject,
                roles = jwt.getClaim("roles")
                    .asList(String::class.java)
            )

        } catch (e: Exception) {

            ValidateResponse(
                valid = false,
                userId = null,
                roles = null
            )
        }
    }

}