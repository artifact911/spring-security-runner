package auth.repository

import auth.dto.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository

@Repository
class UserRepository(
    private val passwordEncoder: PasswordEncoder
) {

    private val users = listOf(
        User(
            id = "1",
            login = "admin",
            passwordHash = passwordEncoder.encode("123"),
            roles = listOf("ADMIN")
        )
    )

    suspend fun findByLogin(login: String): User? {
        return users.find { it.login == login }
    }

}