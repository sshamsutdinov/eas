package ru.eas.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.eas.domain.News

interface NewsRepository : MongoRepository<News, String>