package apiserviciotransporte.apiserviciotransporte.config.security;

import apiserviciotransporte.apiserviciotransporte.config.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthConfig {

    @Value("${application.security.jwt.secret-key}")
    private String jwtSecret;

    @Value("${application.security.jwt.access-token.expiration}")
    private long jwtAccessTokenExp;

    @Value("${application.security.jwt.id-token.expiration}")
    private long jwtIdTokenExp;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtProvider jwtProvider() {
        return JwtProvider.builder()
                .jwtAccessTokenExp(this.jwtAccessTokenExp)
                .jwtIdTokenExp(this.jwtIdTokenExp)
                .jwtSecret(this.jwtSecret)
                .build();
    }
}
