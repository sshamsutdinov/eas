package ru.eas.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("ui_content")
data class UIContent(
    @Id
    val id: UIContentID,
    val content: String
)

enum class UIContentID {
    submit_author_guidelines,
    submit_submission_side,
    submit_open_access_policy,
    submit_purchase,
    about_about_the_journal,
    about_editorial_board,
    about_Terms_and_conditions,
    fees,
    footer_issn,
    footer_copyright,
    footer_registration_date,
    footer_license_num,
    header_indexes
}