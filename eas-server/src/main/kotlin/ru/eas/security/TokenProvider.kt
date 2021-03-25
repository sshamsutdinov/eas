package ru.eas.security

import io.jsonwebtoken.*
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import ru.eas.domain.UserDetailsImpl
import java.time.Duration
import java.util.*

@Component
class TokenProvider {

    companion object : Logging {
        private const val TOKEN_SECRET = "absfdsagqrewq"
        private val TOKEN_EXPIRATION_TIME = Duration.ofDays(10).toMillis()
    }

    fun generate(authentication: Authentication): String {
        val userPrincipal = authentication.principal as UserDetailsImpl

        return Jwts.builder()
            .setSubject(userPrincipal.username)
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + TOKEN_EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
            .compact()
    }

    fun verify(token: String): Boolean {
        try {
            Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(token)
            return true
        } catch (e: SignatureException) {
            logger.error("Invalid JWT signature: {}", e)
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token: {}", e)
        } catch (e: ExpiredJwtException) {
            logger.error("JWT token is expired: {}", e)
        } catch (e: UnsupportedJwtException) {
            logger.error("JWT token is unsupported: {}", e)
        } catch (e: IllegalArgumentException) {
            logger.error("JWT claims string is empty: {}", e)
        }

        return false
    }

    fun getUsername(token: String): String {
        return Jwts.parser().setSigningKey(TOKEN_SECRET)
            .parseClaimsJws(token).body.subject
    }
}