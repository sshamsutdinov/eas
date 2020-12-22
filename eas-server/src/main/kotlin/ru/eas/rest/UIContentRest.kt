package ru.eas.rest

import org.apache.logging.log4j.kotlin.Logging
import org.springframework.web.bind.annotation.*
import ru.eas.domain.UIContent
import ru.eas.repository.UIContentRepository

@RestController
@CrossOrigin(origins = ["http://localhost:4200"])
@RequestMapping("${Api.GENERAL}/content")
class UIContentRest(
    private val uiContentRepository: UIContentRepository
) {

    companion object : Logging

    @GetMapping
    fun get(): Response {
        val content = uiContentRepository.findAll()
        return Response.Success(content)
    }

    @PostMapping
    fun upsert(@RequestBody content: UIContent): Response {
        uiContentRepository.save(content)
        logger.info("Ui content updated $content")
        return Response.Success<Any>()
    }
}