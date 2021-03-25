package ru.eas.rest

import org.apache.logging.log4j.kotlin.Logging
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import ru.eas.domain.Issue
import ru.eas.repository.IssueRepository

@RestController
@CrossOrigin
@RequestMapping("${Api.GENERAL}/issue")
open class IssueRest(
    private val issueRepository: IssueRepository
) {

    companion object : Logging

    @GetMapping
    open fun get(): Response {
        val issues = issueRepository.findAll()
        return Response.Success(issues)
    }

    @PostMapping
    fun upsert(@RequestBody issue: Issue): Response {
        val updatedIssue = issueRepository.save(issue)
        logger.info("Issue updated $updatedIssue")
        return Response.Success<Any>()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): Response {
        issueRepository.deleteById(id)
        logger.info("Issue deleted by id $id")
        return Response.Success<Any>()
    }
}