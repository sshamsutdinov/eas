package ru.eas.domain

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document
data class Issue(
    @Id
    val number: String,
    val doi: String,
    @JsonFormat(pattern = "yyyy-MM-dd")
    val publishingDate: LocalDate,
    val pdfMeta: String,
    val coverMeta: String,
    val articles: List<Article>
)

data class Article(
    val title: String,
    val author: String,
    val authorAbout: String,
    val citation: String,
    val annotation: String,
    val rubric: String,
    val keywords: List<String>,
    val pages: ArticlePages,
    val pdfMeta: String
)

data class ArticlePages(
    val pageStart: Int,
    val pageEnd: Int
)