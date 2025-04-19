package kr.mooner510.dsmpractice.security.config

import com.fasterxml.jackson.databind.ObjectMapper
import kr.mooner510.dsmpractice.security.component.TokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsUtils

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val tokenProvider: TokenProvider,
    private val objectMapper: ObjectMapper,
) {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(Exception::class)
    fun configure(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .csrf(CsrfConfigurer<HttpSecurity>::disable)
            .formLogin(FormLoginConfigurer<HttpSecurity>::disable)
            .logout(LogoutConfigurer<HttpSecurity>::disable)
            .cors { }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests {

                it.requestMatchers(HttpMethod.GET,"/v1/**").permitAll()

                // 로그인 회원가입
                it.requestMatchers(HttpMethod.POST, "/api/auth/sign-up").permitAll()
                it.requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                it.requestMatchers(HttpMethod.POST, "/api/auth/withdraw").permitAll()
                // 게시물 CRUD
                it.requestMatchers(HttpMethod.POST, "/api/post/create").permitAll()
                it.requestMatchers(HttpMethod.GET, "/api/post/read").permitAll()
                it.requestMatchers(HttpMethod.PUT, "/api/post/update/**").permitAll()
                it.requestMatchers(HttpMethod.DELETE, "/api/post/delete/**").permitAll()

                it.anyRequest().denyAll()
            }
            .with(FilterConfiguration(tokenProvider, objectMapper), Customizer.withDefaults())

            .build()
    }
}
