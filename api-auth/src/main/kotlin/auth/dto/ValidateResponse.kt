package auth.dto

data class ValidateResponse(
    val valid: Boolean,
    val userId: String?,
    val roles: List<String>?
)