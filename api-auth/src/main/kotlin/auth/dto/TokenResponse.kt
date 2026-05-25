package auth.dto

data class TokenResponse(
    val accessToken: String,
    val expiresIn: Long
)