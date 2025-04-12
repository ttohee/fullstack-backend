package kr.mooner510.dsmpractice.security.controller

import kr.mooner510.dsmpractice.security.data.request.LoginRequest
import kr.mooner510.dsmpractice.security.data.response.TokenResponse
import kr.mooner510.dsmpractice.security.service.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(private val authService: AuthService) {
    @PostMapping("/login")
    fun login(@RequestBody req: LoginRequest): TokenResponse {
        return authService.login(req)

    }
    @PostMapping("/sign-up")
    fun signUp(@RequestBody req: LoginRequest) {
        authService.signUp(req)
    }
}
