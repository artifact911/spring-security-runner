package auth.configuration

import auth.configuration.props.JwtProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@EnableConfigurationProperties(JwtProperties::class)
@Configuration
class JwtConfig