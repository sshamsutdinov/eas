package ru.eas.security

import org.apache.logging.log4j.kotlin.Logging
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import ru.eas.domain.User
import ru.eas.repository.UserRepository

@Component
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) {
    companion object : Logging

    fun save(user: User): User {
        if (userRepository.existsByUsername(user.username)) {
            throw IllegalArgumentException("Username ${user.username} already exists")
        }

        logger.info("Saving new user: $user")

        return userRepository.save(
            user.copy(
                password = passwordEncoder.encode(user.password)
            )
        )
    }
}