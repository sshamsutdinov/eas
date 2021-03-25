package ru.eas.security

import org.apache.logging.log4j.kotlin.Logging
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthEntryPoint : BasicAuthenticationEntryPoint() {

    companion object : Logging

    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        authException: AuthenticationException?
    ) {
        logger.error("Unauthorized error", authException)
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized")
    }

    override fun afterPropertiesSet() {
        realmName = "localhost"
        super.afterPropertiesSet()
    }
}

