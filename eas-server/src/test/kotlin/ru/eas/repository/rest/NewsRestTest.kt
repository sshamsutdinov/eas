package ru.eas.repository.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import ru.eas.domain.News
import ru.eas.repository.NewsRepository
import ru.eas.rest.GlobalExceptionHandler
import ru.eas.rest.NewsRest
import java.time.LocalDate
import java.time.format.DateTimeFormatter


private const val apiUrl = "/eas/news"

@ContextConfiguration(classes = [NewsRestTest.TestConfiguration::class])
@WebMvcTest(excludeAutoConfiguration = [SecurityAutoConfiguration::class])
class NewsRestTest {

    @Autowired
    private lateinit var mockClient: MockMvc

    @Autowired
    private lateinit var newsRepository: NewsRepository

    private val objectMapper = ObjectMapper().registerModule(
        SimpleModule().apply {
            addSerializer(
                LocalDate::class.java,
                LocalDateSerializer(DateTimeFormatter.ISO_DATE)
            )
        }
    )

    @Test
    fun `when repository throw exception then status fail`() {
        every {
            newsRepository.findAll()
        } throws Exception("test")

        mockClient.perform(MockMvcRequestBuilders.get(apiUrl))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.status", `is`("fail")))
            .andExpect(jsonPath("$.message", `is`("java.lang.Exception: test")))
    }

    @Test
    fun `when repository returns issues then ok`() {
        every {
            newsRepository.findAll()
        } returns news()

        mockClient.perform(MockMvcRequestBuilders.get(apiUrl))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.status", `is`("success")))
            .andExpect(jsonPath("$.data[0].id", `is`("test_news_1")))
            .andExpect(jsonPath("$.data[0].text", `is`("test")))
    }

    @Test
    fun `when repository returns empty list then ok`() {
        every {
            newsRepository.findAll()
        } returns emptyList()

        mockClient.perform(MockMvcRequestBuilders.get(apiUrl))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.status", `is`("success")))
    }

    @Test
    fun `when news is saved correctly then ok`() {
        val news = news().first()
        every {
            newsRepository.save<News>(any())
        } returns news

        mockClient.perform(
            MockMvcRequestBuilders.post(apiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(news))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.status", `is`("success")))
    }

    @Test
    fun `when during saving repo throws exception then fail`() {
        val news = news().first()
        every {
            newsRepository.save<News>(any())
        } throws Exception("test")

        mockClient.perform(
            MockMvcRequestBuilders.post(apiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(news))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.status", `is`("fail")))
            .andExpect(jsonPath("$.message", `is`("java.lang.Exception: test")))
    }

    @Test
    fun `when issue is deleted then ok`() {
        every {
            newsRepository.deleteById("test_news_1")
        } returns Unit

        mockClient.perform(MockMvcRequestBuilders.delete("$apiUrl/test_news_1"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.status", `is`("success")))
    }

    fun news() =
        listOf(
            News(
                id = "test_news_1",
                date = LocalDate.now(),
                text = "test"
            )
        )

    @Configuration
    open class TestConfiguration {

        @Bean
        open fun newsRepository(): NewsRepository = mockk()

        @Bean
        open fun newsRest(newsRepository: NewsRepository): NewsRest = NewsRest(newsRepository)

        @Bean
        open fun globalExceptionHandler(): GlobalExceptionHandler = GlobalExceptionHandler()
    }
}
