package ru.eas.domain.storage

import java.io.InputStream

data class File(
    val fileName: String,
    val contentType: String,
    val stream: InputStream
)