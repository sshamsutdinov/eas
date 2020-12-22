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
import ru.eas.domain.Article
import ru.eas.domain.ArticlePages
import ru.eas.domain.Issue
import ru.eas.repository.IssueRepository
import ru.eas.rest.GlobalExceptionHandler
import ru.eas.rest.IssueRest
import java.time.LocalDate
import java.time.format.DateTimeFormatter


private const val apiUrl = "/eas/issue"

@ContextConfiguration(classes = [IssueRestTest.TestConfiguration::class])
@WebMvcTest
class IssueRestTest {

    @Autowired
    private lateinit var mockClient: MockMvc

    @Autowired
    private lateinit var issueRepository: IssueRepository

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
            issueRepository.findAll()
        } throws Exception("test")

        mockClient.perform(MockMvcRequestBuilders.get(apiUrl))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.status", `is`("fail")))
            .andExpect(jsonPath("$.message", `is`("java.lang.Exception: test")))
    }

    @Test
    fun `when repository returns issues then ok`() {
        every {
            issueRepository.findAll()
        } returns issues()

        mockClient.perform(MockMvcRequestBuilders.get(apiUrl))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.status", `is`("success")))
            .andExpect(jsonPath("$.data[0].number", `is`("issue_number_test")))
            .andExpect(jsonPath("$.data[0].articles[0].title", `is`("article_title_test")))
    }

    @Test
    fun `when repository returns empty list then ok`() {
        every {
            issueRepository.findAll()
        } returns emptyList()

        mockClient.perform(MockMvcRequestBuilders.get(apiUrl))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.status", `is`("success")))
    }

    @Test
    fun `when issue is saved correctly then ok`() {
        val issue = issues().first()
        every {
            issueRepository.save<Issue>(any())
        } returns issue

        mockClient.perform(
            MockMvcRequestBuilders.post(apiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(issue))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.status", `is`("success")))
    }

    @Test
    fun `when during saving repo throws exception then fail`() {
        val issue = issues().first()
        every {
            issueRepository.save<Issue>(any())
        } throws Exception("test")

        mockClient.perform(
            MockMvcRequestBuilders.post(apiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(issue))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.status", `is`("fail")))
            .andExpect(jsonPath("$.message", `is`("java.lang.Exception: test")))
    }

    @Test
    fun `when issue is deleted then ok`() {
        every {
            issueRepository.deleteById("test")
        } returns Unit

        mockClient.perform(MockMvcRequestBuilders.delete("$apiUrl/test"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.status", `is`("success")))
    }

    fun issues() =
        listOf(
            Issue(
                number = "issue_number_test",
                doi = "test",
                publishingDate = LocalDate.now(),
                pdfMeta = "test",
                coverMeta = "test",
                articles = listOf(
                    Article(
                        title = "article_title_test",
                        author = "article_author_test",
                        authorAbout = "article_author_about_test",
                        citation = "article_citation_test",
                        annotation = "article_annotation_test",
                        rubric = "article_rubric_test",
                        keywords = listOf("test_keyword_1", "test_keyword_2"),
                        pages = ArticlePages(
                            pageStart = 5,
                            pageEnd = 12
                        ),
                        pdfMeta = "article_pdf_test"
                    )
                )
            )
        )

    @Configuration
    open class TestConfiguration {

        @Bean
        open fun issueRepository(): IssueRepository = mockk()

        @Bean
        open fun issueRest(issueRepository: IssueRepository): IssueRest = IssueRest(issueRepository)

        @Bean
        open fun globalExceptionHandler(): GlobalExceptionHandler = GlobalExceptionHandler()
    }
}
