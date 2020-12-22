package ru.eas.domain

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document
data class News(
    @Id
    val id: String,
    @JsonFormat(pattern = "yyyy-MM-dd")
    val date: LocalDate,
    val text: String
)