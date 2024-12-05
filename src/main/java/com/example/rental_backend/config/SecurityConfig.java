package com.example.rental_backend.config;

import javax.crypto.spec.SecretKeySpec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.rental_backend.service.CustomUserDetailsService;
import com.nimbusds.jose.jwk.source.ImmutableSecret;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Security configuration for the application.
 * Configures authentication, authorization, JWT handling, and password encoding.
 */
@Configuration
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    private final String jwtKey;

    /**
     * Constructor for dependency injection of the custom user details service and loading environment variables.
     *
     * @param customUserDetailsService the service for loading user-specific data
     */
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;

        // Load the JWT secret key from environment variables using Dotenv
        Dotenv dotenv = Dotenv.load();
        this.jwtKey = dotenv.get("JWT_SECRET_KEY");

        // Ensure the key is present
        if (this.jwtKey == null || this.jwtKey.isEmpty()) {
            throw new IllegalStateException("JWT_SECRET_KEY environment variable is missing or empty.");
        }
    }
	
    /**
     * Configures the security filter chain for HTTP requests.
     *
     * - Disables CSRF (since this is a stateless application using JWTs).
     * - Configures session management as stateless.
     * - Allows public access to registration and login endpoints.
     * - Protects all other endpoints with authentication.
     * - Enables JWT-based OAuth2 resource server.
     *
     * @param http the HttpSecurity object
     * @return the configured SecurityFilterChain
     * @throws Exception if there is an error in the configuration
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {		
        return http
                .csrf(csrf -> csrf.disable()) // Disable CSRF since we're using stateless JWTs
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless sessions
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers( 
                        "/api/auth/register", 
                        "/api/auth/login",
                        "/v3/api-docs/**",    // Endpoints OpenAPI
                        "/swagger-ui/**",     // Swagger UI static files
                        "/swagger-ui.html",   // Swagger UI entry point
                        "/api-docs/swagger-config" ).permitAll() // Public endpoints
                    .anyRequest().authenticated() // All other endpoints require authentication
                ).oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())) // Enable JWT-based OAuth2 resource server
                .build();
    }

    /**
     * Configures the authentication manager to use the custom user details service
     * and a password encoder.
     *
     * Note: This is marked as a temporary fix and should be refactored if possible.
     *
     * @param http the HttpSecurity object
     * @param passwordEncoder the password encoder bean
     * @return the configured AuthenticationManager
     * @throws Exception if there is an error in the configuration
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder).and().build();
    }

    /**
     * Configures the password encoder to use BCrypt hashing.
     * 
     * @return a BCryptPasswordEncoder instance
     */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
	}
    
    /**
     * Configures the JWT encoder with the secret key.
     *
     * @return a configured JwtEncoder
     */
    @Bean
    public JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>(jwtKey.getBytes()));
    }
    
    /**
     * Configures the JWT decoder to validate tokens signed with the secret key.
     * 
     * @return a configured JwtDecoder
     */
    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKeySpec secretKey = new SecretKeySpec(jwtKey.getBytes(), "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();
    }
}