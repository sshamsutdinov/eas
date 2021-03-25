package ru.eas.rest

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import ru.eas.domain.Role
import ru.eas.domain.User
import ru.eas.security.UserService
import ru.eas.security.TokenProvider
import java.util.*


@RestController
@CrossOrigin(origins = ["http://localhost:4200"])
@RequestMapping(Api.AUTH)
class SecurityRest(
    private val userService: UserService,
    private val tokenProvider: TokenProvider,
    private val authenticationManager: AuthenticationManager
) {

    @PostMapping("/signup")
    fun singUp(@RequestBody request: SignUpRequest): Response {
        val user = User(
            //TODO: ID GENERATOR FOR ALL DATA CLASSES
            id = UUID.randomUUID().toString(),
            username = request.username,
            password = request.password,
            role = Role.valueOf(request.role)
        )
        userService.save(user)
        return Response.Success<Any>()
    }

    @PostMapping("/signin")
    fun signIn(@RequestBody signInRequest: SignInRequest): Response {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                signInRequest.username,
                signInRequest.password
            )
        )
        SecurityContextHolder.getContext().apply {
            this.authentication = authentication
        }
        val token = tokenProvider.generate(authentication)
        return Response.Success(data = TokenResponse(token))
    }
}

data class SignInRequest(
    val username: String,
    val password: String
)

data class SignUpRequest(
    val username: String,
    val password: String,
    val role: String
)

data class TokenResponse(
    val token: String
)
