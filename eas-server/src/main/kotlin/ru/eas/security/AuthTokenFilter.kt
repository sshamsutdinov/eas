package ru.eas.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthTokenFilter(
    private val tokenProvider: TokenProvider,
    private val userDetailsService: UserDetailsServiceImpl
) : OncePerRequestFilter() {

    companion object {
        private const val AUTH_HEADER = "Authorization"
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = retrieveToken(request)

        if (token != null && tokenProvider.verify(token)) {
            val username = tokenProvider.getUsername(token)
            val userDetails = userDetailsService.loadUserByUsername(username)
            val authentication = UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
            )

            SecurityContextHolder.getContext().apply {
                this.authentication = authentication
            }
        }

        filterChain.doFilter(request, response)
    }

    private fun retrieveToken(request: HttpServletRequest): String? {
        val authHeader = request.getHeader(AUTH_HEADER)
        return if (isValidAuthHeader(authHeader)) {
            authHeader
        } else {
            null
        }
    }

    private fun isValidAuthHeader(authHeader: String?) =
        authHeader != null &&
                authHeader.isNotBlank() &&
                authHeader.isNotEmpty()
}