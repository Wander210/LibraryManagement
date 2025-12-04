package com.example.library.library_service.dto

import java.time.LocalDateTime

data class ReturnBookDto(
    val id: String, // borrowBookId
    val borrowRecordId: String,
    val bookId: String,
    val borrowTime: LocalDateTime,
    val daysOverdue : Int,
    val dueTime : LocalDateTime,
    val bookTitle : String
)
