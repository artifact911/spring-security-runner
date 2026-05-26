package gateway.configuration

import gateway.configuration.properties.JwtProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder
import org.springframework.security.web.server.SecurityWebFilterChain
import javax.crypto.spec.SecretKeySpec

@Configuration
@EnableWebFluxSecurity
class SecurityConfig(
    private val jwtProperties: JwtProperties
) {

    @Bean
    fun jwtDecoder(): ReactiveJwtDecoder {

        val secretKey = SecretKeySpec(
            jwtProperties.secret.toByteArray(),
            "HmacSHA256"
        )

        return NimbusReactiveJwtDecoder
            .withSecretKey(secretKey)
            .build()
    }

    @Bean
    fun securityFilterChain(
        http: ServerHttpSecurity
    ): SecurityWebFilterChain {

        return http
            .csrf { it.disable() }

            .authorizeExchange {

                it.pathMatchers("/auth/**")
                    .permitAll()

                it.anyExchange()
                    .authenticated()
            }

            .oauth2ResourceServer {
                it.jwt(Customizer.withDefaults())
            }

            .build()
    }

}