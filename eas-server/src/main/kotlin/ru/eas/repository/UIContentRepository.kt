package ru.eas.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.eas.domain.UIContent
import ru.eas.domain.UIContentID

interface UIContentRepository: MongoRepository<UIContent, UIContentID>