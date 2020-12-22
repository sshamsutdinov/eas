package ru.eas.rest

import org.apache.logging.log4j.kotlin.Logging
import org.springframework.web.bind.annotation.*
import ru.eas.domain.News
import ru.eas.repository.NewsRepository

@RestController
@CrossOrigin(origins = ["http://localhost:4200"])
@RequestMapping("${Api.GENERAL}/news")
class NewsRest(
    private val newsRepository: NewsRepository
) {
    companion object : Logging

    @GetMapping
    fun get(): Response {
        val news = newsRepository.findAll()
        return Response.Success(news)
    }

    @PostMapping
    fun upsert(@RequestBody news: News): Response {
        val updatedNews = newsRepository.save(news)
        logger.info("Issue updated $updatedNews")
        return Response.Success<News>()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): Response {
        newsRepository.deleteById(id)
        logger.info("News deleted by id $id")
        return Response.Success<News>()
    }
}