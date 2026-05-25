package auth.service

import auth.configuration.props.JwtProperties
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.stereotype.Service
import java.time.Clock
import java.time.Instant
import java.util.Date

@Service
class JwtService(
    private val properties: JwtProperties,
    private val clock: Clock
) {

    private val algorithm =
        Algorithm.HMAC256(properties.secret)

    fun generateToken(
        userId: String,
        roles: List<String>
    ): String {

        val now = Instant.now(clock)

        return JWT.create()
            .withSubject(userId)
            .withClaim("roles", roles)
            .withIssuer(properties.issuer)
            .withIssuedAt(now)
            .withExpiresAt(
                Date.from(now.plusMillis(properties.expirationMs))
            )
            .sign(algorithm)
    }

    fun validate(token: String): DecodedJWT {

        val verifier = JWT
            .require(algorithm)
            .withIssuer(properties.issuer)
            .build()

        return verifier.verify(token)
    }

}