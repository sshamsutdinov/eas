package ru.eas.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.eas.domain.Issue

interface IssueRepository: MongoRepository<Issue, String>