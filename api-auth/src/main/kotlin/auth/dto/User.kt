package auth.dto

data class User(
    val id: String,
    val login: String,
    val passwordHash: String,
    val roles: List<String>
)