package com.example.library.library_service.dto

import java.time.LocalDateTime

data class BookDto (
    val id: String,
    val title: String,
    val category: String,
    val author: String,
    val publisher: String,
    val publicationYear: Int,
    var description: String = "",
    val availableCopies: Int,
    var totalOfCopies: Int,
    var createdAt: LocalDateTime?,
    val photoUrls: List<String> = emptyList()
)