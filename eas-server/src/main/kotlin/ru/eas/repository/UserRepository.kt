package ru.eas.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import ru.eas.domain.User
import java.util.*

@Repository
interface UserRepository : MongoRepository<User, String> {
    fun findByUsername(username: String): Optional<User>

    fun existsByUsername(username: String): Boolean
}