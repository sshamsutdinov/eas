package ru.eas.repository.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import ru.eas.domain.*
import ru.eas.repository.IssueRepository
import ru.eas.repository.UIContentRepository
import ru.eas.rest.GlobalExceptionHandler
import ru.eas.rest.UIContentRest
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private const val apiUrl = "/eas/content"

@ContextConfiguration(classes = [UIContentRestTest.TestConfiguration::class])
@WebMvcTest
class UIContentRestTest {

    @Autowired
    private lateinit var mockClient: MockMvc

    @Autowired
    private lateinit var uiContentRepository: UIContentRepository

    private val objectMapper = ObjectMapper()

    @Test
    fun `when repository throw exception then status fail`() {
        every {
            uiContentRepository.findAll()
        } throws Exception("test")

        mockClient.perform(MockMvcRequestBuilders.get(apiUrl))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.status", `is`("fail")))
            .andExpect(jsonPath("$.message", `is`("java.lang.Exception: test")))
    }

    @Test
    fun `when repository returns content then ok`() {
        every {
            uiContentRepository.findAll()
        } returns contents()

        mockClient.perform(MockMvcRequestBuilders.get(apiUrl))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.status", `is`("success")))
            .andExpect(jsonPath("$.data[0].id", `is`("${UIContentID.about_Terms_and_conditions}")))
            .andExpect(jsonPath("$.data[0].content", `is`("test")))
    }

    @Test
    fun `when repository returns empty list then ok`() {
        every {
            uiContentRepository.findAll()
        } returns emptyList()

        mockClient.perform(MockMvcRequestBuilders.get(apiUrl))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.status", `is`("success")))
    }

    @Test
    fun `when content is saved correctly then ok`() {
        val content = contents().first()
        every {
            uiContentRepository.save<UIContent>(any())
        } returns content

        mockClient.perform(
            MockMvcRequestBuilders.post(apiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(content))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.status", `is`("success")))
    }

    @Test
    fun `when during saving repo throws exception then fail`() {
        val content = contents().first()
        every {
            uiContentRepository.save<UIContent>(any())
        } throws Exception("test")

        mockClient.perform(
            MockMvcRequestBuilders.post(apiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(content))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.status", `is`("fail")))
            .andExpect(jsonPath("$.message", `is`("java.lang.Exception: test")))
    }

    fun contents() = listOf(
        UIContent(
            id = UIContentID.about_Terms_and_conditions,
            content = "test"
        )
    )

    @Configuration
    open class TestConfiguration {

        @Bean
        open fun uiContentRepository(): UIContentRepository = mockk()

        @Bean
        open fun uiContetntRest(uiContentRepository: UIContentRepository): UIContentRest =
            UIContentRest(uiContentRepository)

        @Bean
        open fun globalExceptionHandler(): GlobalExceptionHandler = GlobalExceptionHandler()
    }
}
