package ru.eas.security

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import ru.eas.domain.UserDetailsImpl
import ru.eas.repository.UserRepository

@Component
class UserDetailsServiceImpl(
    val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        requireNotNull(username) {
            "Username must not be null"
        }

        return userRepository.findByUsername(username)
            .map { UserDetailsImpl(it) }
            .orElseThrow {
                UsernameNotFoundException("Cannot find user by username: $username")
            }
    }
}